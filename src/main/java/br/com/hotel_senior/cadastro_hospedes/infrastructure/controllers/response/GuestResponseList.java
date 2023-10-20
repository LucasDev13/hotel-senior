package br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GuestResponseList(
        @JsonProperty(value = "nome", required = true)
        String name,
        @JsonProperty(value = "documento", required = true)
        String document,
        @JsonProperty(value = "telefone", required = true)
        String telephone
) {

}
