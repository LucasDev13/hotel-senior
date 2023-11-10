package br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers;

import br.com.hotel_senior.cadastro_hospedes.application.usecases.CheckoutUseCase;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.request.GuestCheckoutRequest;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers.RequestAndResponseDomainMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/api/checkout", produces = {"application/json"})
@Tag(name = "Realiza checkout", description = "Endpoint utilizado para realizar o checkout do hospede.")
public class CheckoutGuestController {

    private final RequestAndResponseDomainMapper mapper;
    private final CheckoutUseCase checkoutUseCase;

    public CheckoutGuestController(RequestAndResponseDomainMapper mapper, CheckoutUseCase checkoutUseCase) {
        this.mapper = mapper;
        this.checkoutUseCase = checkoutUseCase;
    }

    @PutMapping
    @Operation(summary = "Realiza uma atualização na reserva do hospede para checkout.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Update realizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Dados de requisição inválida."),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno do sistema."),
    })
    public ResponseEntity<?> updateStatusCheckout(@RequestBody GuestCheckoutRequest guestCheckoutRequest){
        var checkoutDomain = mapper.fromCheckoutRequestToCheckoutDomain(guestCheckoutRequest);
        checkoutUseCase.updateStatusCheckout(checkoutDomain);
        return ResponseEntity.accepted().build();
    }

}
