package br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers;

import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.*;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.request.CheckinHotelRequest;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.request.GuestCheckoutRequest;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.request.GuestRequest;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.request.HotelGuestUpdateRequest;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.response.GuestResponse;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.response.GuestResponseList;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.response.HotelGuestUpdateResponse;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.response.StatusReservationResponse;
import org.springframework.data.domain.Page;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RequestAndResponseDomainMapper {

    public GuestDomain fromRequestToDomain(GuestRequest guestRequest){
        return new GuestDomain(new HotelDomain(guestRequest.hotelRequest().name()),
                                guestRequest.name(), guestRequest.document(), guestRequest.telephone());
    }

    public Page<GuestResponse> fromDomainToResponse(Page<GuestDomainById> guestObjDomain) {
        return guestObjDomain.map(this::convertToObjResponse);
    }

    private GuestResponse convertToObjResponse(GuestDomainById guestDomainById) {
        return new GuestResponse(guestDomainById.id(), guestDomainById.name(),
                guestDomainById.document(), guestDomainById.telephone(),
                guestDomainById.hotelReservation(), guestDomainById.totalCostOfAccommodation());
    }

    public GuestDomainUpdate fromRequestUpdadeToDomain(HotelGuestUpdateRequest hotelGuestUpdateRequest) {
        return new GuestDomainUpdate(hotelGuestUpdateRequest.name(), hotelGuestUpdateRequest.document(), hotelGuestUpdateRequest.telephone());
    }

    public HotelGuestUpdateResponse fromDomainUpdadeToResponse(GuestDomainUpdate guestDomainUpdate) {
        return new HotelGuestUpdateResponse(guestDomainUpdate.name(), guestDomainUpdate.document(), guestDomainUpdate.telephone());
    }

    public CheckinDomain fromRequestToDomain(CheckinHotelRequest checkinHotelRequest){
        return new CheckinDomain(new GuestDomainCheckin(checkinHotelRequest.guestRequestCheckin().name(),
                        checkinHotelRequest.guestRequestCheckin().document(),
                        checkinHotelRequest.guestRequestCheckin().telephone()
                ), checkinHotelRequest.entryDate(), checkinHotelRequest.departureDate(),
                   checkinHotelRequest.vehicleAdditional());
    }

    public List<GuestResponseList> fromDomainListToResponseList(List<GuestDomain> guests) {
        return guests.stream()
                .map(response -> new GuestResponseList(response.name(), response.document(), response.telephone()))
                .collect(Collectors.toList());
    }

    public GuestCheckoutDomain fromCheckoutRequestToCheckoutDomain(GuestCheckoutRequest guestCheckoutRequest){
        return new GuestCheckoutDomain(guestCheckoutRequest.document(), guestCheckoutRequest.checkoutDate());
    }

    public List<StatusReservationResponse> fromDomainToResponseBasedStatusQuery(List<StatusReservationDomain> statusReservationDomains){
        var listToResponse = new ArrayList<StatusReservationResponse>();
        for (StatusReservationDomain reservationDomain :
                statusReservationDomains) {
            listToResponse.add(new StatusReservationResponse(reservationDomain.guest()));
        }
        return listToResponse;
    }
}
