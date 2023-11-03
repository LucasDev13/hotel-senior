package br.com.hotel_senior.cadastro_hospedes.infrastructure.gatewaysAppImpl;

import br.com.hotel_senior.cadastro_hospedes.application.gateways.CheckReservationGateway;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.StatusReservationDomain;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers.DomainAndEntityMapper;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.HotelBookingStatus;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.repositorys.GuestRepository;
import jakarta.transaction.Transactional;

import java.util.List;

public class CheckReservationGatewayImpl implements CheckReservationGateway {

    private final GuestRepository guestRepository;
    private final DomainAndEntityMapper mapper;

    public CheckReservationGatewayImpl(GuestRepository guestRepository, DomainAndEntityMapper mapper) {
         this.guestRepository = guestRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public List<StatusReservationDomain> checkReservationByStatus(String status) {
        var guests = guestRepository.findByHotelReservationHotelBookingStatus(HotelBookingStatus.valueOf(status.toUpperCase()));
        return mapper.fromEntityToDomainBasedStatusQuery(guests);
    }
}