package br.com.hotel_senior.cadastro_hospedes.application.gateways;

import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.CheckinDomain;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomain;

import java.math.BigDecimal;
import java.util.List;

public interface CheckinHotelGateway {
    void registerCheckinHotel(BigDecimal parkingFee, BigDecimal priceHotel, CheckinDomain checkinDomain);

    List<GuestDomain> findGuests(String param);
}
