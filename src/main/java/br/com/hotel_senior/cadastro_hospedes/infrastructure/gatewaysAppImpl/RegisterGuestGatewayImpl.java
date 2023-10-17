package br.com.hotel_senior.cadastro_hospedes.infrastructure.gatewaysAppImpl;

import br.com.hotel_senior.cadastro_hospedes.application.gateways.RegisterGuestGateway;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomain;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers.DomainAndEntityMapper;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.repositorys.GuestRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RegisterGuestGatewayImpl implements RegisterGuestGateway {

    private final GuestRepository guestRepository;
    private final DomainAndEntityMapper mapper;

    public RegisterGuestGatewayImpl(GuestRepository guestRepository, DomainAndEntityMapper mapper) {
        this.guestRepository = guestRepository;
        this.mapper = mapper;
    }

    @Override
    public void registerGuest(GuestDomain guestDomain) {
        var guestObjEntity = mapper.fromDomainToEntity(guestDomain);
        guestRepository.save(guestObjEntity);
        log.info("Objeto salvo!");
    }
}
