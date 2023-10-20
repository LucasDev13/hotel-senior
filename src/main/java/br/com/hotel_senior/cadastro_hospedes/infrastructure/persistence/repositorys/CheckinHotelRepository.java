package br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.repositorys;

import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckinHotelRepository extends JpaRepository<Hotel, Long> {
}
