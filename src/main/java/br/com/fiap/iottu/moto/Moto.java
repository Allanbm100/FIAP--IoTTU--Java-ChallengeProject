package br.com.fiap.iottu.moto;

import br.com.fiap.iottu.patio.Patio;
import br.com.fiap.iottu.statusmoto.StatusMoto;
import br.com.fiap.iottu.tag.Tag;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "TB_MOTO")
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_moto")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_status")
    private StatusMoto status;

    @ManyToOne
    @JoinColumn(name = "id_patio")
    private Patio patio;

    @Column(name = "placa_moto")
    private String placa;

    @Column(name = "chassi_moto")
    private String chassi;

    @Column(name = "nr_motor_moto")
    private String numeroMotor;

    @Column(name = "modelo_moto")
    private String modelo;

    @ManyToMany
    @JoinTable(
            name = "TB_MOTO_TAG",
            joinColumns = @JoinColumn(name = "id_moto"),
            inverseJoinColumns = @JoinColumn(name = "id_tag")
    )
    private List<Tag> tags;
}
