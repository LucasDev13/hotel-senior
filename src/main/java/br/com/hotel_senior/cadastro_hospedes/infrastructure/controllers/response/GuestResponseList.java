package br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GuestResponseList(
        @JsonProperty(value = "nome")
        String name,
        @JsonProperty(value = "documento")
        String document,
        @JsonProperty(value = "telefone")
        String telephone
        ) {}
