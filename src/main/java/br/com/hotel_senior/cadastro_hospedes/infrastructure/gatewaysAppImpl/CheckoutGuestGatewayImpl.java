package br.com.hotel_senior.cadastro_hospedes.infrastructure.gatewaysAppImpl;

import br.com.hotel_senior.cadastro_hospedes.application.gateways.CheckoutGuestGateway;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestCheckoutDomain;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.Guest;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.repositorys.CheckoutHotelRepository;
import jakarta.transaction.Transactional;

public class CheckoutGuestGatewayImpl implements CheckoutGuestGateway {

    private final CheckoutHotelRepository checkoutHotelRepository;

    public CheckoutGuestGatewayImpl(CheckoutHotelRepository checkoutHotelRepository) {
        this.checkoutHotelRepository = checkoutHotelRepository;
    }

    @Override
    @Transactional
    public Guest consultGuestForStatusCheckout(GuestCheckoutDomain checkoutDomain) {
        return checkoutHotelRepository.findByDocument(checkoutDomain.document());
    }

    @Override
    @Transactional
    public Guest saveUpdateStatusCheckout(Guest guest) {
        var saved = checkoutHotelRepository.save(guest);
        System.out.println("Obj guest depois de salvar: " + saved.toString());
        return saved;
    }
}
