package br.com.hotel_senior.cadastro_hospedes.mainConfigBean;

import br.com.hotel_senior.cadastro_hospedes.application.gateways.CheckinHotelGateway;
import br.com.hotel_senior.cadastro_hospedes.application.gateways.GuestGateway;
import br.com.hotel_senior.cadastro_hospedes.application.usecases.CheckinUseCase;
import br.com.hotel_senior.cadastro_hospedes.application.usecases.GuestUseCase;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.gatewaysAppImpl.CheckinHotelGatewayImpl;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.gatewaysAppImpl.GuestGatewayImpl;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers.DomainAndEntityMapper;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers.RequestAndResponseDomainMapper;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.repositorys.CheckinHotelRepository;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.repositorys.GuestRepository;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.repositorys.HotelRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {
    @Bean
    GuestUseCase guestUseCase(GuestGateway guestGateway, RequestAndResponseDomainMapper mapper){
        return new GuestUseCase(guestGateway, mapper);
    }

    @Bean
    CheckinUseCase checkinUseCase(CheckinHotelGateway checkinHotelGateway, RequestAndResponseDomainMapper mapper){
        return new CheckinUseCase(checkinHotelGateway, mapper);
    }

    @Bean
    RequestAndResponseDomainMapper requestToDomainMapper(){
        return new RequestAndResponseDomainMapper();
    }

    @Bean
    GuestGateway registerGuestGateway(GuestRepository guestRepository,
                                      CheckinHotelRepository checkinHotelRepository,
                                      DomainAndEntityMapper mapper,
                                      HotelRepository hotelRepository){
        return new GuestGatewayImpl(guestRepository, mapper, checkinHotelRepository, hotelRepository);
    }

    @Bean
    DomainAndEntityMapper domainAndEntityMapper(){
        return new DomainAndEntityMapper();
    }

    @Bean
    CheckinHotelGateway checkinHotelGateway(DomainAndEntityMapper mapper, CheckinHotelRepository checkinHotelRepository, GuestRepository guestRepository){
        return new CheckinHotelGatewayImpl(mapper, checkinHotelRepository, guestRepository);
    }
}
