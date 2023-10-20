package br.com.hotel_senior.cadastro_hospedes.infrastructure.persistence.entitys;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tbl_checkin_hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "data_entrada", nullable = false)
    private LocalDateTime entryDate;
    @Column(name = "data_saida", nullable = false)
    private LocalDateTime departureDate;
    @Column(name = "adicional_veiculo", nullable = false)
    private Boolean vehicleAdditional;
    @OneToOne(cascade = CascadeType.ALL)
    private Guest guest;
    private BigDecimal priceHotel;
    private BigDecimal parkingFee;

    public Hotel(LocalDateTime entryDate, LocalDateTime departureDate,
                 Boolean vehicleAdditional, Guest guest, BigDecimal priceHotel, BigDecimal parkingFee ) {
        this.entryDate = entryDate;
        this.departureDate = departureDate;
        this.vehicleAdditional = vehicleAdditional;
        this.guest = guest;
        this.priceHotel = priceHotel;
        this.parkingFee = parkingFee;
    }
}
