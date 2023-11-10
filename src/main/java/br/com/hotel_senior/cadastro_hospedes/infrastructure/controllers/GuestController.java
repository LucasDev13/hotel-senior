package br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers;

import br.com.hotel_senior.cadastro_hospedes.application.usecases.GuestUseCase;
import br.com.hotel_senior.cadastro_hospedes.exceptions.ResourceNotFoundException;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.request.GuestRequest;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.request.HotelGuestUpdateRequest;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.response.GuestResponse;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers.RequestAndResponseDomainMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/v1/api/guests", produces = {"application/json"})
@Tag(name = "CRUD do hospede", description = "Endpoints utilizados para o crud do hospede.")
public class GuestController {

    private final GuestUseCase guestUseCase;
    private final RequestAndResponseDomainMapper mapper;

    public GuestController(GuestUseCase guestUseCase, RequestAndResponseDomainMapper mapper) {
        this.guestUseCase = guestUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Registra um hospede no sistema.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hospede registrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Dados de requisição inválida."),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno do sistema."),
    })
    public ResponseEntity<?> saveGuest(@Valid @RequestBody GuestRequest guestRequest, UriComponentsBuilder uriComponentsBuilder){
        var guestObjDomain = mapper.fromRequestToDomain(guestRequest);
        guestUseCase.hotelGuestRegistration(guestObjDomain);
        URI uri = uriComponentsBuilder.path("{guests}").buildAndExpand(guestRequest).toUri();
        return ResponseEntity.created(uri).body(guestRequest);
    }

    @GetMapping
    @Operation(summary = "Realiza um busca paginada no sistema.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Dados de requisição inválida."),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno do sistema."),
    })
    public ResponseEntity<Page<GuestResponse>> listPageableAllGuest(@PageableDefault(sort = "name", page = 0, size = 10) Pageable pagination){
        Page<GuestResponse> response = guestUseCase.consultAllGuests(pagination);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Realiza uma atualização de um hospede no sistema pelo id.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update realizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Dados de requisição inválida."),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno do sistema."),
    })
    public ResponseEntity<?> updateGuest(@PathVariable Long id, @Valid @RequestBody HotelGuestUpdateRequest hotelGuestUpdateRequest){
        var objDomainUpdate = mapper.fromRequestUpdadeToDomain(hotelGuestUpdateRequest);
        var objdomain = guestUseCase.hotelGuestUpdateRequest(id, objDomainUpdate);
        return ResponseEntity.ok().body(mapper.fromDomainUpdadeToResponse(objdomain));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Registra delete do hospede no sistema por meio do id.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = ":NoContent, operação realizada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Dados de requisição inválida."),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno do sistema."),
    })
    public ResponseEntity<Void> deleteGuest(@PathVariable Long id){
        try{
            guestUseCase.deleteGuest(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id not found " + id);
        }
        return ResponseEntity.noContent().build();
    }
}
