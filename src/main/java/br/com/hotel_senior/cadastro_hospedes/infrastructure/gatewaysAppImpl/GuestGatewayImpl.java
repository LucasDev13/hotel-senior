package br.com.hotel_senior.cadastro_hospedes.infrastructure.gatewaysAppImpl;

import br.com.hotel_senior.cadastro_hospedes.application.gateways.GuestGateway;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomain;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomainById;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomainUpdate;
import br.com.hotel_senior.cadastro_hospedes.exceptions.ResourceNotFoundException;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers.DomainAndEntityMapper;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.Guest;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.HotelReservation;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.repositorys.CheckinHotelRepository;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.repositorys.GuestRepository;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.repositorys.HotelRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Slf4j
public class GuestGatewayImpl implements GuestGateway {

    private final GuestRepository guestRepository;
    private final DomainAndEntityMapper mapper;
    private final CheckinHotelRepository checkinHotelRepository;

    private final HotelRepository hotelRepository;

    public GuestGatewayImpl(GuestRepository guestRepository, DomainAndEntityMapper mapper, CheckinHotelRepository checkinHotelRepository, HotelRepository hotelRepository) {
        this.guestRepository = guestRepository;
        this.mapper = mapper;
        this.checkinHotelRepository = checkinHotelRepository;
        this.hotelRepository = hotelRepository;
    }

    @Override
    @Transactional
    public void registerGuest(GuestDomain guestDomain) {
        var guestObjEntity = mapper.fromDomainToEntity(guestDomain);
        var hotel = hotelRepository.findByName(guestDomain.hotelDomain().name());
        guestObjEntity.setHotel(hotel);
        guestRepository.save(guestObjEntity);
        log.info("Objeto salvo!");
    }

    @Override
    @Transactional
    public Page<GuestDomainById> consultAllGuests(Pageable pagination) {
        Page<Guest> guest = guestRepository.findAll(pagination);
        log.info("Objeto paginado retornado!");
        return mapper.convertPageableGuest(guest);
    }

    @Override
    @Transactional
    public GuestDomainUpdate hotelGuestUpdate(Long id, GuestDomainUpdate objUpdate) {
        try {
            var entityObj = guestRepository.getReferenceById(id);
            entityObj.updateInformation(objUpdate);
            entityObj = guestRepository.save(entityObj);
            log.info("Obj " + entityObj.getName() + " salvo" );
            return mapper.fromEntityUpdateToDomain(entityObj);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Override
    @Transactional
    public void deleteGuest(Long id) {
        Guest guest = guestRepository.findById(id).orElse(null);
        if(guest != null){
            HotelReservation reservation = guest.getHotelReservation();
            if(reservation != null){
                reservation.setGuest(null);
                checkinHotelRepository.save(reservation);
            }
        }
        if (guestRepository.existsById(id)) {
            guestRepository.deleteById(id);
            log.info("Guest deleted from the system!");
        } else {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }
}
