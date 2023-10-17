package br.com.hotel_senior.cadastro_hospedes.mainConfigBean;

import br.com.hotel_senior.cadastro_hospedes.application.usecases.GuestUseCase;
import br.com.hotel_senior.cadastro_hospedes.infrastructure.mappers.RequestToDomainMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {
    @Bean
    GuestUseCase guestUseCase(){
        return new GuestUseCase();
    }

    @Bean
    RequestToDomainMapper requestToDomainMapper(){
        return new RequestToDomainMapper();
    }
}
