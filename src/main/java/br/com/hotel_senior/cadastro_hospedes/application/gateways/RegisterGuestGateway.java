package br.com.hotel_senior.cadastro_hospedes.application.gateways;

import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomain;

public interface RegisterGuestGateway {
    void registerGuest(GuestDomain guestDomain);
}
