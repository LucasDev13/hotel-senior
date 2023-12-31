package br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys;

import br.com.hotel_senior.cadastro_hospedes.domain.EntityDomain.GuestDomainUpdate;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "tbl_hospede")
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String name;
    @Column(name = "documento", nullable = false, unique = true)
    private String document;
    @Column(name = "telefone", nullable = false)
    private String telephone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_hotel")
    @ToString.Exclude
    private Hotel hotel;

    @OneToOne
    @ToString.Exclude
    private HotelReservation hotelReservation;

    public Guest() {
    }

    public Guest(String name, String document, String telephone) {
        this.name = name;
        this.document = document;
        this.telephone = telephone;
    }

    public void updateInformation(GuestDomainUpdate objUpdate) {
        this.name = objUpdate.name();
        this.document = objUpdate.document();
        this.telephone = objUpdate.telephone();
    }

    @Override
    public String toString() {
        return "Guest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", document='" + document + '\'' +
                ", telephone='" + telephone + '\'' +
                ", hotel=" + hotel +
                ", hotelReservation=" + hotelReservation +
                '}';
    }
}
