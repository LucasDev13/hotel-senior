package br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "tbl_hospede")
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_hospede", updatable = false, nullable = false)
    private Long id;
    @Column(name = "nome_hospede", updatable = false, nullable = false)
    private String nome;
    @Column(name = "documento_hospede", nullable = false)
    private String documento;
    @Column(name = "telefone_hospede", nullable = false)
    private String telefone;
}
