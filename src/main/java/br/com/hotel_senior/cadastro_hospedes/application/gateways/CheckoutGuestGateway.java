package br.com.hotel_senior.cadastro_hospedes.application.gateways;

import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestCheckoutDomain;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.Guest;

public interface CheckoutGuestGateway {

    Guest consultGuestForStatusCheckout(GuestCheckoutDomain checkoutDomain);

    Guest saveUpdateStatusCheckout(Guest guest);
}
