package br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.response;

import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomainById;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.HotelReservation;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record GuestResponse(
        @JsonProperty(value = "id-hospede", required = true)
        Long id,
        @JsonProperty(value = "nome", required = true)
        String name,
        @JsonProperty(value = "documento", required = true)
        String document,
        @JsonProperty(value = "telefone", required = true)
        String telephone,
        HotelReservation hotelReservation,
        @JsonProperty(value = "valor-total-hospedagem")
        BigDecimal totalCostOfAccommodation
) {
    public GuestResponse(GuestDomainById guestDomainById) {
        this(guestDomainById.id(), guestDomainById.name(), 
                guestDomainById.document(), guestDomainById.telephone(),
                guestDomainById.hotelReservation(),
                guestDomainById.totalCostOfAccommodation());
    }
}
