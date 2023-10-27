package br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys;

public enum HotelBookingStatus {
    CHECKIN("Checkin"),
    CHECKOUT("Checkout");

    private String name;

    HotelBookingStatus(String name) {
        this.name = name;
    }
}
