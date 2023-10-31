package br.com.hotel_senior.cadastro_hospedes.application.usecases;

import br.com.hotel_senior.cadastro_hospedes.application.gateways.CheckoutGuestGateway;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestCheckoutDomain;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomainChechout;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers.DomainAndEntityMapper;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.Guest;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.HotelBookingStatus;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Slf4j
public class CheckoutUseCase {

    private final DomainAndEntityMapper mapper;

    private final CheckoutGuestGateway checkoutGuestGateway;

    public CheckoutUseCase(DomainAndEntityMapper mapper, CheckoutGuestGateway checkoutGuestGateway) {
        this.mapper = mapper;
        this.checkoutGuestGateway = checkoutGuestGateway;
    }

    @Transactional
    public GuestDomainChechout updateStatusCheckout(GuestCheckoutDomain checkoutDomain) {
        var guestEntity = checkoutGuestGateway.consultGuestForStatusCheckout(checkoutDomain);

        if (checkCheckoutDateAndTime(checkoutDomain.checkoutDate(), guestEntity.getHotelReservation().getDepartureDate())) {
            var horaLimite = LocalTime.of(16, 30);
            if(!checkoutDomain.checkoutDate().toLocalTime().isBefore(horaLimite)){
                includeTheAdditionalDailyRate(guestEntity);
            }
            guestEntity.getHotelReservation().setHotelBookingStatus(HotelBookingStatus.CHECKOUT);
            guestEntity.getHotelReservation().setDepartureDate(checkoutDomain.checkoutDate());
            var objSaved = checkoutGuestGateway.saveUpdateStatusCheckout(guestEntity);
            System.out.println("Obj guest Entry: " + guestEntity.toString());

        }
        return mapper.fromEntityToDomainCheckout(guestEntity);
    }

    private boolean checkCheckoutDateAndTime(LocalDateTime dateCheckout, LocalDateTime dateCheckoutGuest) {
        return dateCheckout.toLocalDate().isEqual(dateCheckoutGuest.toLocalDate());
    }

    private void includeTheAdditionalDailyRate(Guest guest) {
        var hotelReservation = guest.getHotelReservation();
        hotelReservation.setPriceHotel(hotelReservation.getPriceHotel().add(new BigDecimal("120.00")));
        guest.setHotelReservation(hotelReservation);
        System.out.println("Obj guest adiciona a taxa adicional: " + guest.toString());
    }
}
