package br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.response;

import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.Guest;

public record StatusReservationResponse(Guest guest){}