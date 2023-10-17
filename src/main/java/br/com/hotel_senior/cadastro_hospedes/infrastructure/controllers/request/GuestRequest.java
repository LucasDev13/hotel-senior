package br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GuestRequest {

    @JsonProperty(value = "nome", required = true)
    @NotNull(message = "Não pode ser null!")
    @NotBlank(message = "Não pode ser em branco!")
    @NotEmpty(message = "Não pode estar vazio!")
    private String name;
    @JsonProperty(value = "documento", required = true)
    @NotNull(message = "Não pode ser null!")
    @NotBlank(message = "Não pode ser em branco!")
    @NotEmpty(message = "Não pode estar vazio!")
    private String document;
    @JsonProperty(value = "telefone", required = true)
    @NotNull(message = "Não pode ser null!")
    @NotBlank(message = "Não pode ser em branco!")
    @NotEmpty(message = "Não pode estar vazio!")
    private String telephone;

}
