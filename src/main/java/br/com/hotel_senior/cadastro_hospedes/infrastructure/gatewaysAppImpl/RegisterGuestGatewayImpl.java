package br.com.hotel_senior.cadastro_hospedes.infrastructure.gatewaysAppImpl;

import br.com.hotel_senior.cadastro_hospedes.application.gateways.RegisterGuestGateway;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomain;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomainUpdate;
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

    @Override
    @Transactional
    public GuestDomainUpdate hotelGuestUpdate(Long id, GuestDomainUpdate objUpdate) {
        //passo 6: mapper para uma entidade do banco.
        //var guestObjEntityUpdate = mapper.fromDomainToEntityUpdate(objUpdate);

        //passo 7: chamar o repository guestRepository.getReferenceById() para buscar a referencia da entidade no banco.
        //Usando o getReferenceById()  eu toco o banco de dados apenas uma vez pegando a referencia do objeto
        var entityObj = guestRepository.getReferenceById(id);

        //atualizar objeto entity
        entityObj.updateInformation(objUpdate);
        /*entityObj.setName(objUpdate.name());
        entityObj.setDocument(objUpdate.document());
        entityObj.setTelephone(objUpdate.telephone());*/
        log.info(entityObj.getName());

        entityObj = guestRepository.save(entityObj);//salva objeto atualizado mas com probleminha aqui, não está atualizando.

        //Guest save = guestRepository.save(entityObj);
        log.info("Obj salvo");
        GuestDomainUpdate guestDomainUpdate = mapper.fromEntityUpdateToDomain(entityObj);

        return guestDomainUpdate;
    }
}
