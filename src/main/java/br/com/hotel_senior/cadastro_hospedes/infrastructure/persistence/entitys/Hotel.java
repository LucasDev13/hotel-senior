package br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public Hotel() {}

    public Hotel(String name) {
        this.name = name;
    }
}
