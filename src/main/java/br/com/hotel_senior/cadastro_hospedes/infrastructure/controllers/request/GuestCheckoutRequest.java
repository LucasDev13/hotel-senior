package br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.request;

import br.com.hotel_senior.cadastro_hospedes.application.validations.CurrentOrFutureDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record GuestCheckoutRequest(
        @JsonProperty(value = "documento", required = true)
        @NotBlank(message = "Documento não pode estar em branco")
        String document,
        @JsonProperty(value = "dataCheckout", required = true)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd hh:mm:ss")
        //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @CurrentOrFutureDate(message = "A data do checkout deve estar no presente.")
        LocalDateTime checkoutDate) {}
