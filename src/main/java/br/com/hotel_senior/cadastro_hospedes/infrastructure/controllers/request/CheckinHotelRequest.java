package br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record CheckinHotelRequest(
        @JsonProperty(value = "hospede", required = true)
        @NotNull(message = "Não pode ser null!")
        GuestRequestCheckin guestRequestCheckin,
        @JsonProperty(value = "dataEntrada", required = true)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd/MM/yyy")
        LocalDateTime entryDate,
        @JsonProperty(value = "dataSaida", required = true)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd/MM/yyy")
        LocalDateTime departureDate,
        @JsonProperty(value = "adicionalVeiculo", required = true)
        @NotNull(message = "Não pode ser null!")
        Boolean vehicleAdditional) {
}
