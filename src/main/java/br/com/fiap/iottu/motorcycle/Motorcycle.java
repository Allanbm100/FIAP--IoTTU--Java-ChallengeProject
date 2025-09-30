package br.com.fiap.iottu.motorcycle;

import br.com.fiap.iottu.yard.Yard;
import br.com.fiap.iottu.motorcyclestatus.MotorcycleStatus;
import br.com.fiap.iottu.tag.Tag;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "TB_MOTO")
public class Motorcycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_moto")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_status")
    private MotorcycleStatus status;

    @ManyToOne
    @JoinColumn(name = "id_patio")
    private Yard yard;

    @Column(name = "placa_moto")
    private String licensePlate;

    @Column(name = "chassi_moto")
    private String chassi;

    @Column(name = "nr_motor_moto")
    private String engineNumber;

    @Column(name = "modelo_moto")
    private String model;

    @ManyToMany
    @JoinTable(
            name = "TB_MOTO_TAG",
            joinColumns = @JoinColumn(name = "id_moto"),
            inverseJoinColumns = @JoinColumn(name = "id_tag")
    )
    private List<Tag> tags;
}
