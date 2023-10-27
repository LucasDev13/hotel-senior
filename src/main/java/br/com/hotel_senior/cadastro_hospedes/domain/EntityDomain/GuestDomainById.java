package br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain;

import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.HotelReservation;

import java.math.BigDecimal;

public record GuestDomainById(
        Long id,
        String name,
        String document,
        String telephone,
        HotelReservation hotelReservation,
        BigDecimal totalCostOfAccommodation
){
}
