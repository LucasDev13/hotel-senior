package br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain;

public record GuestDomain(HotelDomain hotelDomain, String name, String document, String telephone){}
