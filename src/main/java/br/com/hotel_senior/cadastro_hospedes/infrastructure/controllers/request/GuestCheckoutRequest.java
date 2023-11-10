package br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.request;

import br.com.hotel_senior.cadastro_hospedes.application.validations.CurrentOrFutureDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "Informações do checkout")
public record GuestCheckoutRequest(
        @JsonProperty(value = "documento", required = true)
        @NotBlank(message = "Documento não pode estar em branco")
        @Schema(description = "Documento", example = "04985729318")
        String document,
        @JsonProperty(value = "dataCheckout", required = true)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd hh:mm:ss")
        @CurrentOrFutureDate(message = "A data do checkout deve estar no presente.")
        @Schema(description = "Data do checkout", example = "2023-11-05T17:30:00")
        LocalDateTime checkoutDate) {}
