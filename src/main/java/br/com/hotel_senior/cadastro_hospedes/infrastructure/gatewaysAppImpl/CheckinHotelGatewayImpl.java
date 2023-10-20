package br.com.hotel_senior.cadastro_hospedes.infrastructure.gatewaysAppImpl;

import br.com.hotel_senior.cadastro_hospedes.application.gateways.CheckinHotelGateway;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.CheckinDomain;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers.DomainAndEntityMapper;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.Guest;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.Hotel;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.repositorys.CheckinHotelRepository;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.repositorys.GuestRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CheckinHotelGatewayImpl implements CheckinHotelGateway {

    private final DomainAndEntityMapper mapper;
    private final CheckinHotelRepository checkinHotelRepository;
    private final GuestRepository guestRepository;
    public CheckinHotelGatewayImpl(DomainAndEntityMapper mapper, CheckinHotelRepository checkinHotelRepository, GuestRepository guestRepository) {
        this.mapper = mapper;
        this.checkinHotelRepository = checkinHotelRepository;
        this.guestRepository = guestRepository;
    }

    @Override
    public void registerCheckinHotel(CheckinDomain checkinDomain) {
        Hotel hotel = mapper.fromDomainToEntity(checkinDomain);
        Guest guest = guestRepository.findByDocument(checkinDomain.guestDomain().document());
        hotel.setGuest(guest);
        checkinHotelRepository.save(hotel);
        log.info("Objeto salvo!");
    }
}
