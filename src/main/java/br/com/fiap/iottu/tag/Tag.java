package br.com.fiap.iottu.tag;

import br.com.fiap.iottu.motorcycle.Motorcycle;
import jakarta.persistence.*;
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

    @Column(name = "codigo_rfid_tag")
    private String rfidCode;

    @Column(name = "ssid_wifi_tag")
    private String wifiSsid;

    @Column(name = "latitude_tag", precision = 10, scale = 6)
    private BigDecimal latitude;

    @Column(name = "longitude_tag", precision = 10, scale = 6)
    private BigDecimal longitude;

    @Column(name = "data_hora_tag")
    private LocalDateTime timestamp;

    @ManyToMany(mappedBy = "tags")
    private List<Motorcycle> motorcycles;
}
