package br.com.hotel_senior.cadastro_hospedes.application.usecases;

import br.com.hotel_senior.cadastro_hospedes.application.gateways.CheckinHotelGateway;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.CheckinDomain;

public class CheckinUseCase {

    private final CheckinHotelGateway checkinHotelGateway;

    public CheckinUseCase(CheckinHotelGateway checkinHotelGateway) {
        this.checkinHotelGateway = checkinHotelGateway;
    }

    public void registerCheckinHotel(CheckinDomain checkinDomain) {
        checkinHotelGateway.registerCheckinHotel(checkinDomain);
    }
}
