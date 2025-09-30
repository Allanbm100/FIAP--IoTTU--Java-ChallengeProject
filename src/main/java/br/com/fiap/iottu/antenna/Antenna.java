package br.com.fiap.iottu.antenna;

import br.com.fiap.iottu.yard.Yard;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "TB_ANTENA")
public class Antenna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_antena")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_patio")
    private Yard yard;

    @Column(name = "codigo_antena")
    private String code;

    @Column(name = "latitude_antena", precision = 10, scale = 6)
    private BigDecimal latitude;

    @Column(name = "longitude_antena", precision = 10, scale = 6)
    private BigDecimal longitude;
}
