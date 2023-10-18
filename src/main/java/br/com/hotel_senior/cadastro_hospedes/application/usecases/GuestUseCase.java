package br.com.hotel_senior.cadastro_hospedes.application.usecases;

import br.com.hotel_senior.cadastro_hospedes.application.gateways.RegisterGuestGateway;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomain;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomainUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class GuestUseCase{

    private final RegisterGuestGateway registerGuestGateway;

    public GuestUseCase(RegisterGuestGateway registerGuestGateway) {
        this.registerGuestGateway = registerGuestGateway;
    }


    public void hotelGuestRegistration(GuestDomain guestDomain) {
        registerGuestGateway.registerGuest(guestDomain);
    }

    public Page<GuestDomain> consultAllGuests(Pageable pagination) {
        return registerGuestGateway.consultAllGuests(pagination);
    }

    public GuestDomainUpdate hotelGuestUpdateRequest(Long id, GuestDomainUpdate objDomainUpdate) {
        var returnObjDomain = registerGuestGateway.hotelGuestUpdate(id, objDomainUpdate);
        return returnObjDomain;
    }

}