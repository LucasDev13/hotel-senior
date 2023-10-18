package br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers;

import br.com.hotel_senior.cadastro_hospedes.application.usecases.GuestUseCase;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomain;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.request.GuestRequest;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.response.GuestResponse;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers.RequestAndResponseDomainMapper;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/v1/api/guests", produces = {"application/json"})
public class GuestController {

    private final GuestUseCase guestUseCase;
    private final RequestAndResponseDomainMapper mapper;

    public GuestController(GuestUseCase guestUseCase, RequestAndResponseDomainMapper mapper) {
        this.guestUseCase = guestUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> saveGuest(@Valid @RequestBody GuestRequest guestRequest, UriComponentsBuilder uriComponentsBuilder){
        var guestObjDomain = mapper.fromRequestToDomain(guestRequest);
        guestUseCase.hotelGuestRegistration(guestObjDomain);
        URI uri = uriComponentsBuilder.path("{guests}").buildAndExpand(guestRequest).toUri();
        return ResponseEntity.created(uri).body(guestRequest);
    }

    @GetMapping
    public Page<GuestResponse> listPageableAllGuest(@PageableDefault(direction = Sort.Direction.ASC, sort = "nome", page = 0, size = 10) Pageable pagination){
            Page<GuestDomain> guestObjDomain = guestUseCase.consultAllGuests(pagination);
            var pageableResponse = mapper.fromDomainToResponse(guestObjDomain);
            return pageableResponse;
    }
}
