package br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record HotelGuestUpdateRequest(
        @JsonProperty(value = "nome", required = true)
        @NotBlank(message = "Não pode estar em branco e ser null!")
        @NotEmpty(message = "Não pode estar vazio!")
        String name,
        @JsonProperty(value = "documento", required = true)
        @NotBlank(message = "Não pode estar em branco e ser null!")
        @NotEmpty(message = "Não pode estar vazio!")
        @Pattern(regexp = "\\d{11}")
        String document,
        @JsonProperty(value = "telefone", required = true)
        @NotBlank(message = "Não pode estar em branco e ser null!")
        @NotEmpty(message = "Não pode estar vazio!")
        @Pattern(regexp = "\\d{12}")
        String telephone
) {
}
