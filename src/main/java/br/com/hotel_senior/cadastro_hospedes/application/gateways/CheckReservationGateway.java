package br.com.hotel_senior.cadastro_hospedes.application.gateways;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.StatusReservationDomain;
import java.util.List;

public interface CheckReservationGateway {

    List<StatusReservationDomain> checkReservationByStatus(String status);
}