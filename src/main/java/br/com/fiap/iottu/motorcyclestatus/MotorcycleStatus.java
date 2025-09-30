package br.com.fiap.iottu.motorcyclestatus;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_STATUS_MOTO")
public class MotorcycleStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status")
    private Integer id;

    @Column(name = "descricao_status")
    private String description;
}
