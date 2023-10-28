package br.com.hotel_senior.cadastro_hospedes.infrastructure.gatewaysAppImpl;

import br.com.hotel_senior.cadastro_hospedes.application.gateways.CheckoutGuestGateway;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestCheckoutDomain;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers.DomainAndEntityMapper;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.Guest;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.repositorys.CheckoutHotelRepository;

public class CheckoutGuestGatewayImpl implements CheckoutGuestGateway {
    private final DomainAndEntityMapper mapper;
    private final CheckoutHotelRepository checkoutHotelRepository;

    public CheckoutGuestGatewayImpl(DomainAndEntityMapper mapper, CheckoutHotelRepository checkoutHotelRepository) {
        this.mapper = mapper;
        this.checkoutHotelRepository = checkoutHotelRepository;
    }

    @Override
    public void updateStatusCheckout(GuestCheckoutDomain checkoutDomain) {
        Guest guest =checkoutHotelRepository.findByDocument(checkoutDomain.document());
    }
}
