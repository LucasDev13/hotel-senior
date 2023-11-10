package br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers;


import br.com.hotel_senior.cadastro_hospedes.application.usecases.ConsultUsecase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/api/consult", produces = {"application/json"})
@Tag(name = "Consulta status da reserva", description = "Consulta todos os hospede com status de checkin ou checkout.")
public class ConsultAllStatusReservationController {

    private final ConsultUsecase consultUsecase;

    public ConsultAllStatusReservationController(ConsultUsecase consultUsecase) {
        this.consultUsecase = consultUsecase;
    }

    @GetMapping(value = "/{status}")
    @Operation(summary = "Consulta as reservas dos hospedes pelo status de 'checkin' e 'checkout'.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Dados de requisição inválida."),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno do sistema."),
    })
    public ResponseEntity<?> consultAllReservation(
            @Parameter(description = "Recebe o status da hospedagem do hospede - checkin ou checkout") @PathVariable String status){
        var response = consultUsecase.checkReservationByStatus(status);
        return ResponseEntity.ok().body(response);
    }
}