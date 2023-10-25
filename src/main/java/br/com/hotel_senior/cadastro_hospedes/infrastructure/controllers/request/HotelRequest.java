package br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record   HotelRequest(
        @JsonProperty(value = "nome", required = true)
        String name
) {
}
