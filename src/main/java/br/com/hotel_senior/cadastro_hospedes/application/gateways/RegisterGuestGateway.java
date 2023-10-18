package br.com.hotel_senior.cadastro_hospedes.application.gateways;

import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RegisterGuestGateway {
    void registerGuest(GuestDomain guestDomain);

    Page<GuestDomain> consultAllGuests(Pageable pagination);
}
