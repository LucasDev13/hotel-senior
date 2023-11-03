package br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers;


import br.com.hotel_senior.cadastro_hospedes.application.usecases.ConsultUsecase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/api/consult", produces = {"application/json"})
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
    public ResponseEntity<?> consultAllReservation(@PathVariable String status){
        var response = consultUsecase.checkReservationByStatus(status);
        return ResponseEntity.ok().body(response);
    }
}