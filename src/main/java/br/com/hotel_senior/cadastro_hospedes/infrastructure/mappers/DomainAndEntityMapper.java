package br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers;

import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.*;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.Guest;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.HotelReservation;
import org.springframework.data.domain.Page;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DomainAndEntityMapper {

    public Guest fromDomainToEntity(GuestDomain domain){
        return new Guest(domain.name(), domain.document(), domain.telephone());
    }

    public Page<GuestDomainById> convertPageableGuest(Page<Guest> guests) {
        return guests.map(this::convertToObjDomain);
    }

    private GuestDomainById convertToObjDomain(Guest guest) {
        return new GuestDomainById(guest.getId(), guest.getName(), guest.getDocument(),
                                    guest.getTelephone(), guest.getHotelReservation(), BigDecimal.ONE);
    }

    public Guest fromDomainToEntityUpdate(GuestDomainUpdate domain) {
        return new Guest(domain.name(), domain.document(), domain.telephone());
    }

    public GuestDomainUpdate fromEntityUpdateToDomain(Guest entity) {
        return new GuestDomainUpdate(entity.getName(), entity.getDocument(), entity.getTelephone());
    }

    public HotelReservation fromDomainToEntity(CheckinDomain checkinDomain){
        List<Guest> guests = new ArrayList<>();
        var guest = new Guest(checkinDomain.guestDomainCheckin().name(),
                checkinDomain.guestDomainCheckin().document(),
                checkinDomain.guestDomainCheckin().telephone());
        guests.add(guest);
        return new HotelReservation(guest, checkinDomain.entryDate(), checkinDomain.departureDate(),
                checkinDomain.vehicleAdditional(), BigDecimal.ZERO, BigDecimal.ZERO);
    }

    public List<GuestDomain> fromEntityListToDomainList(List<Guest> guestsParam) {
        return null; /*guestsParam.stream()
                .map( guest -> new GuestDomain(guest.getName(), guest.getDocument(), guest.getTelephone()))
                .collect(Collectors.toList());*/
    }

    public GuestDomainChechout fromEntityToDomainCheckout(Guest guest) {
        return new GuestDomainChechout(
                guest.getId(),
                guest.getName(),
                guest.getDocument(),
                guest.getTelephone(),
                new HotelDomain(guest.getHotel().getName()),
                new HotelReservationDomain(
                        guest.getHotelReservation().getEntryDate(),
                        guest.getHotelReservation().getDepartureDate(),
                        guest.getHotelReservation().getVehicleAdditional(),
                        guest.getHotelReservation().getPriceHotel(),
                        guest.getHotelReservation().getParkingFee(),
                        guest.getHotelReservation().getHotelBookingStatus()
        ));
    }

    public List<StatusReservationDomain> fromEntityToDomainBasedStatusQuery(List<Guest> guests){
        var listToDomain = new ArrayList<StatusReservationDomain>();
        for (Guest guest :
                guests) {
            listToDomain.add(new StatusReservationDomain(guest));
        }
        return listToDomain;
    }
}
