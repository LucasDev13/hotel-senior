package br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers;

import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.CheckinDomain;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomain;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomainById;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomainUpdate;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.Guest;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.Hotel;
import org.springframework.data.domain.Page;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class DomainAndEntityMapper {

    public Guest fromDomainToEntity(GuestDomain domain){
        return new Guest(domain.name(), domain.document(), domain.telephone());
    }

    public Page<GuestDomainById> convertPageableGuest(Page<Guest> guests) {
        Page<GuestDomainById> pageDomain = guests.map(this::convertToObjDomain);
        return pageDomain;
    }

    private GuestDomainById convertToObjDomain(Guest guest) {
        GuestDomainById domain = new GuestDomainById(guest.getId(), guest.getName(), guest.getDocument(), guest.getTelephone());
        return domain;
    }

    public Guest fromDomainToEntityUpdate(GuestDomainUpdate domain) {
        return new Guest(domain.name(), domain.document(), domain.telephone());
    }

    public GuestDomainUpdate fromEntityUpdateToDomain(Guest entity) {
        return new GuestDomainUpdate(entity.getName(), entity.getDocument(), entity.getTelephone());
    }

    public Hotel fromDomainToEntity(CheckinDomain checkinDomain){
        return new Hotel(checkinDomain.entryDate(),checkinDomain.departureDate(),
                checkinDomain.vehicleAdditional(),
                new Guest(checkinDomain.guestDomain().name(),
                          checkinDomain.guestDomain().document(),
                          checkinDomain.guestDomain().telephone()),
                BigDecimal.ZERO, BigDecimal.ZERO);
    }

    public List<GuestDomain> fromEntityListToDomainList(List<Guest> guestsParam) {
        return guestsParam.stream()
                .map( guest -> new GuestDomain(guest.getName(), guest.getDocument(), guest.getTelephone()))
                .collect(Collectors.toList());
    }
}
