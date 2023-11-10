package br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Schema
public record GuestRequestCheckin(
        @JsonProperty(value = "nome", required = true)
        @NotBlank(message = "Não pode ser em branco e null!")
        @NotEmpty(message = "Não pode estar vazio!")
        @Schema(description = "nome", example = "Lucas Pontes")
        String name,
        @JsonProperty(value = "documento", required = true)
        @NotBlank(message = "Não pode ser em branco e null!")
        @NotEmpty(message = "Não pode estar vazio!")
        @Pattern(regexp = "\\d{11}")
        @Schema(description = "documento", example = "12345678910")
        String document,
        @JsonProperty(value = "telefone", required = true)
        @NotNull(message = "Não pode ser null!")
        @NotBlank(message = "Não pode ser em branco!")
        @NotEmpty(message = "Não pode estar vazio!")
        @Pattern(regexp = "\\d{12}")
        @Schema(description = "telefone", example = "041994833305")
        String telephone
) {
}
