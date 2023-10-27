package br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.request;

import br.com.hotel_senior.cadastro_hospedes.application.validations.CurrentOrFutureDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record CheckinHotelRequest(
        @JsonProperty(value = "hospede", required = true)
        @NotNull(message = "Não pode ser null!")
        GuestRequestCheckin guestRequestCheckin,
        @JsonProperty(value = "dataEntrada", required = true)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd hh:mm:ss")
        //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @CurrentOrFutureDate(message = "A data de checkin deve estar no presente.")
        LocalDateTime entryDate,
        @JsonProperty(value = "dataSaida", required = true)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd hh:mm:ss")
        //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @Future(message = "A data de checkout deve estar no futuro.")
        LocalDateTime departureDate,
        @JsonProperty(value = "adicionalVeiculo", required = true)
        @NotNull(message = "Não pode ser null!")
        Boolean vehicleAdditional)
{}
