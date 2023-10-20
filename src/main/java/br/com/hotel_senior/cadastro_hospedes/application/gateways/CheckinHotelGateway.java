package br.com.hotel_senior.cadastro_hospedes.application.gateways;

import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.CheckinDomain;

public interface CheckinHotelGateway {
    void registerCheckinHotel(CheckinDomain checkinDomain);
}
