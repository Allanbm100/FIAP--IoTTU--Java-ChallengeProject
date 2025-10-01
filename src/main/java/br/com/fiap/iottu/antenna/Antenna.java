package br.com.fiap.iottu.antenna;

import br.com.fiap.iottu.yard.Yard;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "O pátio não pode ser nulo.")
    @ManyToOne
    @JoinColumn(name = "id_patio")
    private Yard yard;

    @NotBlank(message = "O código da antena não pode estar vazio.")
    @Size(min = 3, max = 50, message = "O código da antena deve ter entre 3 e 50 caracteres.")
    @Column(name = "codigo_antena")
    private String code;

    @NotNull(message = "A latitude não pode ser nula.")
    @DecimalMin(value = "-90.0", message = "A latitude mínima é -90.0.")
    @DecimalMax(value = "90.0", message = "A latitude máxima é 90.0.")
    @Column(name = "latitude_antena", precision = 10, scale = 6)
    private BigDecimal latitude;

    @NotNull(message = "A longitude não pode ser nula.")
    @DecimalMin(value = "-180.0", message = "A longitude mínima é -180.0.")
    @DecimalMax(value = "180.0", message = "A longitude máxima é 180.0.")
    @Column(name = "longitude_antena", precision = 10, scale = 6)
    private BigDecimal longitude;
}
