package br.com.hotel_senior.cadastro_hospedes.application.usecases;

import br.com.hotel_senior.cadastro_hospedes.application.gateways.CheckoutGuestGateway;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestCheckoutDomain;

public class CheckoutUseCase {

    //injetar o gateway para a camada de persistencia
    //enviar o guestCheckoutDomain para a camada de persistencia
    private final CheckoutGuestGateway checkoutGuestGateway;

    public CheckoutUseCase(CheckoutGuestGateway checkoutGuestGateway) {
        this.checkoutGuestGateway = checkoutGuestGateway;
    }

    public void updateStatusCheckout(GuestCheckoutDomain checkoutDomain) {
        checkoutGuestGateway.updateStatusCheckout(checkoutDomain);
    }
}
