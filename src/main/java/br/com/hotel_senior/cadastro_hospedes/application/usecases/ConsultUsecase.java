package br.com.hotel_senior.cadastro_hospedes.application.usecases;

import br.com.hotel_senior.cadastro_hospedes.application.gateways.CheckReservationGateway;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.response.StatusReservationResponse;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers.RequestAndResponseDomainMapper;
import java.util.List;

public class ConsultUsecase {

    private final CheckReservationGateway checkReservationGateway;
    private final RequestAndResponseDomainMapper mapper;

    public ConsultUsecase(CheckReservationGateway checkReservationGateway, RequestAndResponseDomainMapper mapper) {
        this.checkReservationGateway = checkReservationGateway;
        this.mapper = mapper;
    }

    public List<StatusReservationResponse> checkReservationByStatus(String status) {
        var response = checkReservationGateway.checkReservationByStatus(status);
        return mapper.fromDomainToResponseBasedCheckinStatusQuery(response);
    }
}