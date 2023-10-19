package br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers;

import br.com.hotel_senior.cadastro_hospedes.application.usecases.GuestUseCase;
import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomainById;
import br.com.hotel_senior.cadastro_hospedes.exceptions.ResourceNotFoundException;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.request.GuestRequest;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.request.HotelGuestUpdateRequest;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.controllers.response.GuestResponse;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers.RequestAndResponseDomainMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.dao.EmptyResultDataAccessException;
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
    @Transactional
    public ResponseEntity<?> saveGuest(@Valid @RequestBody GuestRequest guestRequest, UriComponentsBuilder uriComponentsBuilder){
        var guestObjDomain = mapper.fromRequestToDomain(guestRequest);
        guestUseCase.hotelGuestRegistration(guestObjDomain);
        URI uri = uriComponentsBuilder.path("{guests}").buildAndExpand(guestRequest).toUri();
        return ResponseEntity.created(uri).body(guestRequest);
    }

    @GetMapping
    public Page<GuestResponse> listPageableAllGuest(@PageableDefault(direction = Sort.Direction.ASC, sort = "name", page = 0, size = 10) Pageable pagination){
            Page<GuestDomainById> guestObjDomain = guestUseCase.consultAllGuests(pagination);
            return mapper.fromDomainToResponse(guestObjDomain);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateGuest(@PathVariable Long id, @Valid @RequestBody HotelGuestUpdateRequest hotelGuestUpdateRequest){
        var objDomainUpdate = mapper.fromRequestUpdadeToDomain(hotelGuestUpdateRequest);
        var objdomain = guestUseCase.hotelGuestUpdateRequest(id, objDomainUpdate);
        return ResponseEntity.ok().body(mapper.fromDomainUpdadeToResponse(objdomain));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable Long id){
        try{
            guestUseCase.deleteGuest(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id not found " + id);
        }
        return ResponseEntity.noContent().build();
    }
}
