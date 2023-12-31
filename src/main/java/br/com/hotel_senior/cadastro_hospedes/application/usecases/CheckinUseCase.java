package br.com.hotel_senior.cadastro_hospedes.application.usecases;

import br.com.hotel_senior.cadastro_hospedes.application.gateways.CheckinHotelGateway;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.CheckinDomain;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomain;
import br.com.hotel_senior.cadastro_hospedes.exceptions.InvalidCheckInDateException;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.response.GuestResponseList;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers.RequestAndResponseDomainMapper;
import lombok.extern.slf4j.Slf4j;
import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
public class CheckinUseCase {

    private final CheckinHotelGateway checkinHotelGateway;
    private final RequestAndResponseDomainMapper mapper;
    private BigDecimal priceHotel;
    private BigDecimal parkingFee;
    private final ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

    public CheckinUseCase(CheckinHotelGateway checkinHotelGateway, RequestAndResponseDomainMapper mapper) {
        this.checkinHotelGateway = checkinHotelGateway;
        this.mapper = mapper;
    }

    public void registerCheckinHotel(CheckinDomain checkinDomain) {
        priceHotel = BigDecimal.ZERO;
        parkingFee = BigDecimal.ZERO;

        var dateToday = LocalDateTime.now(zoneId);
        var dayOfWeek = dateToday.getDayOfWeek();
        Long days = ChronoUnit.DAYS.between(checkinDomain.entryDate(), checkinDomain.departureDate());

        if(isValidCheckinDate(checkinDomain.entryDate(), checkinDomain.departureDate())){
            if(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY){
                priceHotel = BigDecimal.valueOf(150.00);
            }
            if(days >= 2){
                priceHotel = BigDecimal.valueOf(120.00);
                priceHotel = priceHotel.multiply(BigDecimal.valueOf(days));
                log.info(priceHotel.toString());
            }else {
                priceHotel = BigDecimal.valueOf(120.00);
            }
        }
        if(checkinDomain.vehicleAdditional()){
            if(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY){
                parkingFee = BigDecimal.valueOf(20.00);
            }
            if(days >= 2){
                parkingFee = BigDecimal.valueOf(15.00);
                parkingFee = parkingFee.multiply(new BigDecimal(days.toString()));
                log.info(parkingFee.toString());
            }else {
                parkingFee = BigDecimal.valueOf(15.00);
            }
        }
        checkinHotelGateway.registerCheckinHotel(parkingFee, priceHotel, checkinDomain);
        priceHotel = BigDecimal.ZERO;
        parkingFee = BigDecimal.ZERO;
    }

    public List<GuestResponseList> findGuests(String param) {
        List<GuestDomain> guests = checkinHotelGateway.findGuests(param);
        return mapper.fromDomainListToResponseList(guests);
    }

    private Boolean isValidCheckinDate(LocalDateTime entryDate, LocalDateTime departureDate) {
        LocalDate ontem = LocalDate.now(ZoneId.of("America/Sao_Paulo")).minusDays(1);
        LocalDate today = LocalDate.now(ZoneOffset.UTC);

        var yesterday = entryDate.minusDays(1);

        if(entryDate.isAfter(yesterday))
            if (entryDate.isBefore(departureDate) || departureDate.isAfter(entryDate)) {
                return true;
            }
        throw new InvalidCheckInDateException("Invalid check-in date. Please provide the correct date.");
    }
}
