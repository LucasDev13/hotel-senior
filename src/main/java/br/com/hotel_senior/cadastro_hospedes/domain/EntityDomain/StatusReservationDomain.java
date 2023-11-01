package br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain;

import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.Guest;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.Hotel;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.HotelReservation;

public record StatusReservationDomain(
        Guest guest,
        Hotel hotel,
        HotelReservation hotelReservation
) {}