package br.com.hotel_senior.cadastro_hospedes.application.usecases;

import br.com.hotel_senior.cadastro_hospedes.application.gateways.GuestGateway;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomain;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomainById;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomainUpdate;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.response.GuestResponse;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers.RequestAndResponseDomainMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.util.List;

public class GuestUseCase {

    private final GuestGateway guestGateway;
    private final RequestAndResponseDomainMapper mapper;

    public GuestUseCase(GuestGateway guestGateway, RequestAndResponseDomainMapper mapper) {
        this.guestGateway = guestGateway;
        this.mapper = mapper;
    }


    public void hotelGuestRegistration(GuestDomain guestDomain) {
        guestGateway.registerGuest(guestDomain);
    }

    public Page<GuestResponse> consultAllGuests(Pageable pagination) {
        var ref = new Object() {
            BigDecimal totalCostOfAccommodation = BigDecimal.ZERO;
        };

        Page<GuestDomainById> domainById = guestGateway.consultAllGuests(pagination);

        for (GuestDomainById domain :
                domainById.getContent()) {
            BigDecimal precoTotalReserva = domain.hotelReservation().getPriceHotel();//aqui está dando null
            BigDecimal taxaEstacionamento = domain.hotelReservation().getParkingFee();
            ref.totalCostOfAccommodation = ref.totalCostOfAccommodation.add(precoTotalReserva).add(taxaEstacionamento);
        }

        List<GuestDomainById> listaAtualizada = domainById.getContent().stream()
                .map(elemento -> new GuestDomainById(elemento.id(),
                        elemento.name(), elemento.document(),
                        elemento.telephone(), elemento.hotelReservation(),
                        ref.totalCostOfAccommodation))
                .toList();

        Page<GuestDomainById> pageAtualizada = new PageImpl<>(listaAtualizada, domainById.getPageable(), listaAtualizada.size());
        return mapper.fromDomainToResponse(pageAtualizada);
    }

    public GuestDomainUpdate hotelGuestUpdateRequest(Long id, GuestDomainUpdate objDomainUpdate) {
        return guestGateway.hotelGuestUpdate(id, objDomainUpdate);
    }

    public void deleteGuest(Long id) {
        guestGateway.deleteGuest(id);
    }
}