package br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.repositorys;

import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckoutHotelRepository extends JpaRepository<Guest, Long> {
    Guest findByDocument(String document);
}
