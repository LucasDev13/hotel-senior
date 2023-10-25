package br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain;

import java.time.LocalDateTime;

public record CheckinDomain(
        GuestDomainCheckin guestDomainCheckin,
        LocalDateTime entryDate,
        LocalDateTime departureDate,
        Boolean vehicleAdditional) {
}
