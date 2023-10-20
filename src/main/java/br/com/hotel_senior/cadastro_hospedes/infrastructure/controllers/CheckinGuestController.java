package br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers;

import br.com.hotel_senior.cadastro_hospedes.application.usecases.CheckinUseCase;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.CheckinDomain;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.request.CheckinHotelRequest;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.response.GuestResponseList;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers.RequestAndResponseDomainMapper;
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
    public ResponseEntity<?> checkinHotel(@Valid @RequestBody CheckinHotelRequest checkinHotelRequest,
                                          UriComponentsBuilder uriComponentsBuilder){
        CheckinDomain checkinDomain = mapper.fromRequestToDomain(checkinHotelRequest);
        checkinUseCase.registerCheckinHotel(checkinDomain);
        URI uri = uriComponentsBuilder.path("{checkin}").buildAndExpand(checkinHotelRequest).toUri();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/guests")
    public ResponseEntity<?> consultByParam(@RequestParam("param") String param){
        List<GuestResponseList> guests = new ArrayList<>();
        if(!param.isEmpty()){
            guests = checkinUseCase.findGuests(param);
        }
        return ResponseEntity.ok().body(guests);
    }
}