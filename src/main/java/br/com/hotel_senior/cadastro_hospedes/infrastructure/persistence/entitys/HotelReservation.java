package br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tbl_hotel_reservation")
public class HotelReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hospede_id")
    @JsonIgnore
    @ToString.Exclude
    private Guest guest;

    @Column(name = "data_entrada")
    private LocalDateTime entryDate;
    @Column(name = "data_saida")
    private LocalDateTime departureDate;
    @Column(name = "adicional_veiculo")
    private Boolean vehicleAdditional;

    @Column(name = "preco_diaria")
    private BigDecimal priceHotel;
    @Column(name = "valor_garagem")
    private BigDecimal parkingFee;

    @Enumerated(EnumType.STRING)
    private HotelBookingStatus hotelBookingStatus;

    public HotelReservation() {}

    public HotelReservation(Guest guest, LocalDateTime entryDate, LocalDateTime departureDate,
                            Boolean vehicleAdditional, BigDecimal priceHotel,
                            BigDecimal parkingFee) {
        this.guest = guest;
        this.entryDate = entryDate;
        this.departureDate = departureDate;
        this.vehicleAdditional = vehicleAdditional;
        this.priceHotel = priceHotel;
        this.parkingFee = parkingFee;
    }
}
