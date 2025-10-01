package br.com.fiap.iottu.tag;

import br.com.fiap.iottu.motorcycle.Motorcycle;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "TB_TAG")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tag")
    private Integer id;

    @NotBlank(message = "O código RFID não pode estar vazio.")
    @Size(min = 5, max = 20, message = "O código RFID deve ter entre 5 e 20 caracteres.")
    @Column(name = "codigo_rfid_tag")
    private String rfidCode;

    @NotBlank(message = "O SSID Wi-Fi não pode estar vazio.")
    @Size(min = 2, max = 32, message = "O SSID Wi-Fi deve ter entre 2 e 32 caracteres.")
    @Column(name = "ssid_wifi_tag")
    private String wifiSsid;

    @NotNull(message = "A latitude não pode ser nula.")
    @DecimalMin(value = "-90.0", message = "A latitude mínima é -90.0.")
    @DecimalMax(value = "90.0", message = "A latitude máxima é 90.0.")
    @Column(name = "latitude_tag", precision = 10, scale = 6)
    private BigDecimal latitude;

    @NotNull(message = "A longitude não pode ser nula.")
    @DecimalMin(value = "-180.0", message = "A longitude mínima é -180.0.")
    @DecimalMax(value = "180.0", message = "A longitude máxima é 180.0.")
    @Column(name = "longitude_tag", precision = 10, scale = 6)
    private BigDecimal longitude;

    @Column(name = "data_hora_tag")
    private LocalDateTime timestamp;

    @ManyToMany(mappedBy = "tags")
    private List<Motorcycle> motorcycles;

    @PrePersist
    public void prePersist() {
        timestamp = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        timestamp = LocalDateTime.now();
    }
}
