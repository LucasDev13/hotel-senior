package br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers;

import br.com.hotel_senior.cadastro_hospedes.application.usecases.CheckinUseCase;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.CheckinDomain;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.request.CheckinHotelRequest;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers.RequestAndResponseDomainMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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
    public ResponseEntity<?> checkinHotel(@Valid @RequestBody CheckinHotelRequest checkinHotelRequest,
                                          UriComponentsBuilder uriComponentsBuilder){
        CheckinDomain checkinDomain = mapper.fromRequestToDomain(checkinHotelRequest);
        checkinUseCase.registerCheckinHotel(checkinDomain);
        URI uri = uriComponentsBuilder.path("{checkin}").buildAndExpand(checkinHotelRequest).toUri();
        return ResponseEntity.ok().build();
    }
}