package br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain;

import java.time.LocalDateTime;

public record GuestCheckoutDomain(String document, LocalDateTime checkoutDate) {
}
