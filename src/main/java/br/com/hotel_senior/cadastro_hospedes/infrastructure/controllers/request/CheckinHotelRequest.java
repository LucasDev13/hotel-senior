package br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.request;

import br.com.hotel_senior.cadastro_hospedes.application.validations.CurrentOrFutureDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "Informações do checkin")
public record CheckinHotelRequest(
        @JsonProperty(value = "hospede", required = true)
        @NotNull(message = "Não pode ser null!")
        @Schema(description = "Informações do hóspede", implementation = GuestRequestCheckin.class)
        GuestRequestCheckin guestRequestCheckin,
        @JsonProperty(value = "dataEntrada", required = true)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd hh:mm:ss")
        @CurrentOrFutureDate(message = "A data de checkin deve estar no presente.")
        @Schema(description = "Data de entrada", example = "2023-11-03T18:00:00")
        LocalDateTime entryDate,
        @JsonProperty(value = "dataSaida", required = true)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd hh:mm:ss")
        @Future(message = "A data de checkout deve estar no futuro.")
        @Schema(description = "Data de saída", example = "2023-11-05T17:30:00")
        LocalDateTime departureDate,
        @JsonProperty(value = "adicionalVeiculo", required = true)
        @NotNull(message = "Não pode ser null!")
        @Schema(description = "Reserva de garagem.", example = "true")
        Boolean vehicleAdditional)
{}
