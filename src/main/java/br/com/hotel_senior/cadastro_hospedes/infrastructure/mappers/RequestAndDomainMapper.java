package br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers;

import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomain;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.request.GuestRequest;

public class RequestAndDomainMapper {

    public GuestDomain fromRequestToDomain(GuestRequest guestRequest){
        return new GuestDomain(guestRequest.getName(), guestRequest.getDocument(), guestRequest.getTelephone());
    }
}
