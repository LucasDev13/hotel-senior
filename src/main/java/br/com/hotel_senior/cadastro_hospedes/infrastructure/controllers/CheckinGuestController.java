package br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers;

import br.com.hotel_senior.cadastro_hospedes.application.usecases.CheckinUseCase;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.CheckinDomain;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.request.CheckinHotelRequest;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.response.GuestResponseList;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers.RequestAndResponseDomainMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/api/checkin", produces = {"application/json"})
public class CheckinGuestController {

    private final CheckinUseCase checkinUseCase;
    private final RequestAndResponseDomainMapper mapper;

    public CheckinGuestController(CheckinUseCase checkinUseCase, RequestAndResponseDomainMapper mapper) {
        this.checkinUseCase = checkinUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Realiza o checkin do hospede no sistema.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Checkin realizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Dados de requisição inválida."),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno do sistema."),
    })
    public ResponseEntity<?> checkinHotel(@Valid @RequestBody CheckinHotelRequest checkinHotelRequest,
                                          UriComponentsBuilder uriComponentsBuilder){
        CheckinDomain checkinDomain = mapper.fromRequestToDomain(checkinHotelRequest);
        checkinUseCase.registerCheckinHotel(checkinDomain);
        URI uri = uriComponentsBuilder.path("{checkin}").buildAndExpand(checkinHotelRequest).toUri();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/guests")
    @Operation(summary = "Consulta com base nos parametros de nome, documento e telefone", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Consulta realizada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Dados de requisição inválida."),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno do sistema."),
    })
    public ResponseEntity<?> consultByParam(@RequestParam("param") String param){
        List<GuestResponseList> guests = new ArrayList<>();
        if(!param.isEmpty()){
            guests = checkinUseCase.findGuests(param);
        }
        return ResponseEntity.ok().body(guests);
    }
}