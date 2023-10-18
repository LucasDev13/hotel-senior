package br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers;

import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomain;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.request.GuestRequest;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.response.GuestResponse;
import org.springframework.data.domain.Page;

public class RequestAndResponseDomainMapper {

    public GuestDomain fromRequestToDomain(GuestRequest guestRequest){
        return new GuestDomain(guestRequest.name(), guestRequest.document(), guestRequest.telephone());
    }

    public Page<GuestResponse> fromDomainToResponse(Page<GuestDomain> guestObjDomain) {
        Page<GuestResponse> pageResponse = guestObjDomain.map(this::convertToObjResponse);
        return pageResponse;
    }

    private GuestResponse convertToObjResponse(GuestDomain guestDomain) {
        GuestResponse response = new GuestResponse(guestDomain.name(), guestDomain.document(), guestDomain.telephone());
        return response;
    }
}
