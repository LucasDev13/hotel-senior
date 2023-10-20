package br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.repositorys;

import br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    Guest findByDocument(String document);

    List<Guest> findByNameContainingIgnoreCaseOrDocumentContainingOrTelephoneContaining(String name, String document, String telephone);
}
