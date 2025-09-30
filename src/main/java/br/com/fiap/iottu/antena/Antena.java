package br.com.fiap.iottu.antena;

import br.com.fiap.iottu.patio.Patio;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "TB_ANTENA")
public class Antena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_antena")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_patio")
    private Patio patio;

    @Column(name = "codigo_antena")
    private String codigo;

    @Column(name = "latitude_antena", precision = 10, scale = 6)
    private BigDecimal latitude;

    @Column(name = "longitude_antena", precision = 10, scale = 6)
    private BigDecimal longitude;
}