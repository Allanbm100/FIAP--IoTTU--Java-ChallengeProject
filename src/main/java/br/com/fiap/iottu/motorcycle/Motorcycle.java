package br.com.fiap.iottu.motorcycle;

import br.com.fiap.iottu.yard.Yard;
import br.com.fiap.iottu.motorcyclestatus.MotorcycleStatus;
import br.com.fiap.iottu.tag.Tag;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "O status da moto não pode ser nulo.")
    @ManyToOne
    @JoinColumn(name = "id_status")
    private MotorcycleStatus status;

    @NotNull(message = "O pátio da moto não pode ser nulo.")
    @ManyToOne
    @JoinColumn(name = "id_patio")
    private Yard yard;

    @NotBlank(message = "A placa da moto não pode estar vazia.")
    @Size(min = 7, max = 7, message = "A placa da moto deve ter 7 caracteres.")
    @Column(name = "placa_moto")
    private String licensePlate;

    @NotBlank(message = "O chassi da moto não pode estar vazio.")
    @Size(min = 17, max = 17, message = "O chassi da moto deve ter 17 caracteres.")
    @Column(name = "chassi_moto")
    private String chassi;

    @NotBlank(message = "O número do motor da moto não pode estar vazio.")
    @Size(min = 5, max = 20, message = "O número do motor deve ter entre 5 e 20 caracteres.")
    @Column(name = "nr_motor_moto")
    private String engineNumber;

    @NotBlank(message = "O modelo da moto não pode estar vazio.")
    @Size(min = 2, max = 50, message = "O modelo da moto deve ter entre 2 e 50 caracteres.")
    @Column(name = "modelo_moto")
    private String model;

    @ManyToMany
    @JoinTable(
            name = "TB_MOTO_TAG",
            joinColumns = @JoinColumn(name = "id_moto"),
            inverseJoinColumns = @JoinColumn(name = "id_tag")
    )
    private List<Tag> tags;

    @Transient
    private Integer selectedTagId;
}
