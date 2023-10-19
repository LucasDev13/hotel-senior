package br.com.hotel_senior.cadastro_hospedes.application.usecases;

import br.com.hotel_senior.cadastro_hospedes.application.gateways.GuestGateway;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomain;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomainById;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomainUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class GuestUseCase{

    private final GuestGateway guestGateway;

    public GuestUseCase(GuestGateway guestGateway) {
        this.guestGateway = guestGateway;
    }


    public void hotelGuestRegistration(GuestDomain guestDomain) {
        guestGateway.registerGuest(guestDomain);
    }

    public Page<GuestDomainById> consultAllGuests(Pageable pagination) {
        return guestGateway.consultAllGuests(pagination);
    }

    public GuestDomainUpdate hotelGuestUpdateRequest(Long id, GuestDomainUpdate objDomainUpdate) {
        return guestGateway.hotelGuestUpdate(id, objDomainUpdate);
    }

    public void deleteGuest(Long id) {
        guestGateway.deleteGuest(id);
    }
}