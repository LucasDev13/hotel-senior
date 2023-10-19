package br.com.hotel_senior.cadastro_hospedes.application.gateways;

import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomain;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomainById;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomainUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GuestGateway {
    void registerGuest(GuestDomain guestDomain);

    Page<GuestDomainById> consultAllGuests(Pageable pagination);

    GuestDomainUpdate hotelGuestUpdate(Long id, GuestDomainUpdate objUpdate);

    void deleteGuest(Long id);
}
