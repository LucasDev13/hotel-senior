package br.com.hotel_senior.cadastro_hospedes.application.usecases;

import br.com.hotel_senior.cadastro_hospedes.application.gateways.CheckinHotelGateway;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.CheckinDomain;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomain;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.response.GuestResponseList;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers.RequestAndResponseDomainMapper;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class CheckinUseCase {

    private final CheckinHotelGateway checkinHotelGateway;
    private final RequestAndResponseDomainMapper mapper;
    private BigDecimal priceHotel;
    private BigDecimal parkingFee;
    private ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

    public CheckinUseCase(CheckinHotelGateway checkinHotelGateway, RequestAndResponseDomainMapper mapper) {
        this.checkinHotelGateway = checkinHotelGateway;
        this.mapper = mapper;
    }

    public void registerCheckinHotel(CheckinDomain checkinDomain) {
        var dateToday = LocalDateTime.now(zoneId);
        var dayOfWeek = dateToday.getDayOfWeek();

        if(isValidCheckinDate(dateToday, checkinDomain.entryDate(), checkinDomain.departureDate())){
            if(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY){
                priceHotel = BigDecimal.valueOf(150.00);
            }
            priceHotel = BigDecimal.valueOf(120.00);
        }
        if(checkinDomain.vehicleAdditional()){
            if(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY){
                parkingFee = BigDecimal.valueOf(20.00);
            }
            parkingFee = BigDecimal.valueOf(15.00);
        }
        checkinHotelGateway.registerCheckinHotel(parkingFee, priceHotel, checkinDomain);
    }

    public List<GuestResponseList> findGuests(String param) {
        List<GuestDomain> guests = checkinHotelGateway.findGuests(param);
        return mapper.fromDomainListToResponseList(guests);
    }

    private Boolean isValidCheckinDate(LocalDateTime dateToday, LocalDateTime entryDate, LocalDateTime departureDate) {
        return dateToday.isBefore(departureDate) && dateToday.isAfter(entryDate);
    }
}
