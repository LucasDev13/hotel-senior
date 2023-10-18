package br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers;

import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomain;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.Guest;
import org.springframework.data.domain.Page;

public class DomainAndEntityMapper {

    public Guest fromDomainToEntity(GuestDomain domain){
        return new Guest(domain.name(), domain.document(), domain.telephone());
    }

    public Page<GuestDomain> convertPageableGuest(Page<Guest> guests) {
        Page<GuestDomain> pageDomain = guests.map(this::convertToObjDomain);
        return pageDomain;
    }

    private GuestDomain convertToObjDomain(Guest guest) {
        GuestDomain domain = new GuestDomain(guest.getNome(), guest.getDocumento(), guest.getTelefone());
        return domain;
    }
}
