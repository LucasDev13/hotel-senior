package br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.response;

import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.Guest;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.Hotel;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.HotelReservation;

public record StatusReservationResponse(
        Guest guest,
        Hotel hotel,
        HotelReservation hotelReservation
) {}