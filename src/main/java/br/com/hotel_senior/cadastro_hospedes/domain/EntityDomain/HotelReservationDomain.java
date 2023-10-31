package br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain;

import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.HotelBookingStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record HotelReservationDomain(
        LocalDateTime entryDate,
        LocalDateTime departureDate,
        Boolean vehicleAdditional,
        BigDecimal priceHotel,
        BigDecimal parkingFee,
        HotelBookingStatus hotelBookingStatus

) {}