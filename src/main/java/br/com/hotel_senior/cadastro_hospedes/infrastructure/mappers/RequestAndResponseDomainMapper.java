package br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers;

import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomain;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomainById;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomainUpdate;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.request.GuestRequest;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.request.HotelGuestUpdateRequest;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.response.GuestResponse;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.response.HotelGuestUpdateResponse;
import org.springframework.data.domain.Page;

public class RequestAndResponseDomainMapper {

    public GuestDomain fromRequestToDomain(GuestRequest guestRequest){
        return new GuestDomain(guestRequest.name(), guestRequest.document(), guestRequest.telephone());
    }

    public Page<GuestResponse> fromDomainToResponse(Page<GuestDomainById> guestObjDomain) {
        Page<GuestResponse> pageResponse = guestObjDomain.map(this::convertToObjResponse);
        return pageResponse;
    }

    private GuestResponse convertToObjResponse(GuestDomainById guestDomainById) {
        GuestResponse response = new GuestResponse(guestDomainById.id(), guestDomainById.name(), guestDomainById.document(), guestDomainById.telephone());
        return response;
    }

    public GuestDomainUpdate fromRequestUpdadeToDomain(HotelGuestUpdateRequest hotelGuestUpdateRequest) {
        return new GuestDomainUpdate(hotelGuestUpdateRequest.name(), hotelGuestUpdateRequest.document(), hotelGuestUpdateRequest.telephone());
    }

    public HotelGuestUpdateResponse fromDomainUpdadeToResponse(GuestDomainUpdate guestDomainUpdate) {
        return new HotelGuestUpdateResponse(guestDomainUpdate.name(), guestDomainUpdate.document(), guestDomainUpdate.telephone());
    }
}
