package br.com.hotel_senior.cadastro_hospedes.mainConfigBean;

import br.com.hotel_senior.cadastro_hospedes.application.gateways.RegisterGuestGateway;
import br.com.hotel_senior.cadastro_hospedes.application.usecases.GuestUseCase;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.gatewaysAppImpl.RegisterGuestGatewayImpl;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers.DomainAndEntityMapper;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers.RequestAndDomainMapper;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.repositorys.GuestRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {
    @Bean
    GuestUseCase guestUseCase(RegisterGuestGateway registerGuestGateway){
        return new GuestUseCase(registerGuestGateway);
    }

    @Bean
    RequestAndDomainMapper requestToDomainMapper(){
        return new RequestAndDomainMapper();
    }

    @Bean
    RegisterGuestGateway registerGuestGateway(GuestRepository guestRepository, DomainAndEntityMapper mapper){
        return new RegisterGuestGatewayImpl(guestRepository, mapper);
    }

    @Bean
    DomainAndEntityMapper domainAndEntityMapper(){
        return new DomainAndEntityMapper();
    }
}
