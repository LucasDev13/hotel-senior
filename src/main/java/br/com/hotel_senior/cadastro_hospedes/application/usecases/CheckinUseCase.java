package br.com.hotel_senior.cadastro_hospedes.application.usecases;

import br.com.hotel_senior.cadastro_hospedes.application.gateways.CheckinHotelGateway;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.CheckinDomain;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomain;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.response.GuestResponseList;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers.RequestAndResponseDomainMapper;
import java.util.List;

public class CheckinUseCase {

    private final CheckinHotelGateway checkinHotelGateway;
    private final RequestAndResponseDomainMapper mapper;

    public CheckinUseCase(CheckinHotelGateway checkinHotelGateway, RequestAndResponseDomainMapper mapper) {
        this.checkinHotelGateway = checkinHotelGateway;
        this.mapper = mapper;
    }

    public void registerCheckinHotel(CheckinDomain checkinDomain) {
        checkinHotelGateway.registerCheckinHotel(checkinDomain);
    }

    public List<GuestResponseList> findGuests(String param) {
        List<GuestDomain> guests = checkinHotelGateway.findGuests(param);
        return mapper.fromDomainListToResponseList(guests);
    }
}
