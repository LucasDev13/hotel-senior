package br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain;

public record GuestDomainChechout(
        Long id,
        String name,
        String document,
        String telephone,
        HotelDomain hotelDomain,
        HotelReservationDomain hotelReservationDomain
) {}