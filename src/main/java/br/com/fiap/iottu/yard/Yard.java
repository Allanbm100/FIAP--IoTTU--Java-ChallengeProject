package br.com.fiap.iottu.yard;

import br.com.fiap.iottu.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_PATIO")
public class Yard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_patio")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User user;

    @Column(name = "cep_patio")
    private String zipCode;

    @Column(name = "numero_patio")
    private String number;

    @Column(name = "cidade_patio")
    private String city;

    @Column(name = "estado_patio")
    private String state;

    @Column(name = "capacidade_patio")
    private Integer capacity;
}
