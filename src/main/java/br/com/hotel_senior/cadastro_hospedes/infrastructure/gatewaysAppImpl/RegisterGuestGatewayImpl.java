package br.com.hotel_senior.cadastro_hospedes.infrastructure.gatewaysAppImpl;

import br.com.hotel_senior.cadastro_hospedes.application.gateways.RegisterGuestGateway;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomain;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers.DomainAndEntityMapper;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.Guest;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.repositorys.GuestRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Slf4j
public class RegisterGuestGatewayImpl implements RegisterGuestGateway {

    private final GuestRepository guestRepository;
    private final DomainAndEntityMapper mapper;

    public RegisterGuestGatewayImpl(GuestRepository guestRepository, DomainAndEntityMapper mapper) {
        this.guestRepository = guestRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void registerGuest(GuestDomain guestDomain) {
        var guestObjEntity = mapper.fromDomainToEntity(guestDomain);
        guestRepository.save(guestObjEntity);
        log.info("Objeto salvo!");
    }

    @Override
    @Transactional
    public Page<GuestDomain> consultAllGuests(Pageable pagination) {
        Page<Guest> guest = guestRepository.findAll(pagination);
        log.info("Objeto paginado retornado!");
        return mapper.convertPageableGuest(guest);
    }
}
