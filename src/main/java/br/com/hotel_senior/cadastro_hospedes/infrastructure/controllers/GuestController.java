package br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers;

import br.com.hotel_senior.cadastro_hospedes.application.usecases.GuestUseCase;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.request.GuestRequest;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers.RequestToDomainMapper;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/v1/api/guests", produces = {"application/json"})
public class GuestController {

    private final GuestUseCase guestUseCase;
    private final RequestToDomainMapper mapper;

    public GuestController(GuestUseCase guestUseCase, RequestToDomainMapper mapper) {
        this.guestUseCase = guestUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> saveGuest(@RequestBody GuestRequest guestRequest, UriComponentsBuilder uriComponentsBuilder){
        var guestObjDomain = mapper.fromRequestToDomain(guestRequest);
        guestUseCase.hotelGuestRegistration(guestObjDomain);
        URI uri = uriComponentsBuilder.path("{guests}").buildAndExpand(guestRequest).toUri();
        return ResponseEntity.created(uri).body(guestRequest);
    }
}
