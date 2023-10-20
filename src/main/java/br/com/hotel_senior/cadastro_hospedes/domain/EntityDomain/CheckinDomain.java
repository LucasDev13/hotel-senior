package br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain;

import java.time.LocalDateTime;

public record CheckinDomain(
        GuestDomain guestDomain,
        LocalDateTime entryDate,
        LocalDateTime departureDate,
        Boolean vehicleAdditional) {
}
