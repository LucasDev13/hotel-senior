package br.com.hotel_senior.cadastro_hospedes.application.usecases;

import br.com.hotel_senior.cadastro_hospedes.application.gateways.RegisterGuestGateway;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomain;

public class GuestUseCase{

    private final RegisterGuestGateway registerGuest;

    public GuestUseCase(RegisterGuestGateway registerGuest) {
        this.registerGuest = registerGuest;
    }


    public void hotelGuestRegistration(GuestDomain guestDomain) {

        registerGuest.registerGuest(guestDomain);

    }
}
