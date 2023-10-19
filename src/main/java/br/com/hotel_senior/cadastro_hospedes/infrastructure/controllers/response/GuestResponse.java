package br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.response;

import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomainById;
import com.fasterxml.jackson.annotation.JsonProperty;

public record GuestResponse(
        @JsonProperty(value = "id-hospede", required = true)
        Long id,
        @JsonProperty(value = "nome", required = true)
        String name,
        @JsonProperty(value = "documento", required = true)
        String document,
        @JsonProperty(value = "telefone", required = true)
        String telephone
) {
    public GuestResponse(GuestDomainById guestDomainById) {
        this(guestDomainById.id(), guestDomainById.name(), guestDomainById.document(), guestDomainById.telephone());
    }
}
