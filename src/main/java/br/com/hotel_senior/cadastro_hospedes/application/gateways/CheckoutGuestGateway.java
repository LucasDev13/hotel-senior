package br.com.hotel_senior.cadastro_hospedes.application.gateways;

import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestCheckoutDomain;

public interface CheckoutGuestGateway {

    void updateStatusCheckout(GuestCheckoutDomain checkoutDomain);
}
