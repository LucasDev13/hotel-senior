package br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.response;

import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomain;
import com.fasterxml.jackson.annotation.JsonProperty;

public record GuestResponse (
    @JsonProperty(value = "nome", required = true)
    String name,
    @JsonProperty(value = "documento", required = true)
    String document,
    @JsonProperty(value = "telefone", required = true)
    String telephone
){
    public GuestResponse(GuestDomain guestDomain){
        this(guestDomain.name(), guestDomain.document(), guestDomain.telephone());
    }
}
