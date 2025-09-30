package br.com.fiap.iottu.patio;

import br.com.fiap.iottu.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_PATIO")
public class Patio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_patio")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User usuario;

    @Column(name = "cep_patio")
    private String cep;

    @Column(name = "numero_patio")
    private String numero;

    @Column(name = "cidade_patio")
    private String cidade;

    @Column(name = "estado_patio")
    private String estado;

    @Column(name = "capacidade_patio")
    private Integer capacidade;
}