package br.com.fiap.iottu.yard;

import br.com.fiap.iottu.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_PATIO")
public class Yard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_patio")
    private Integer id;

    @NotNull(message = "O usuário responsável não pode ser nulo.")
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User user;

    @NotBlank(message = "O CEP não pode estar vazio.")
    @Size(min = 8, max = 8, message = "O CEP deve ter 8 caracteres.")
    @Column(name = "cep_patio")
    private String zipCode;

    @NotBlank(message = "O número não pode estar vazio.")
    @Size(min = 1, max = 10, message = "O número deve ter entre 1 e 10 caracteres.")
    @Column(name = "numero_patio")
    private String number;

    @NotBlank(message = "A cidade não pode estar vazia.")
    @Size(min = 2, max = 50, message = "A cidade deve ter entre 2 e 50 caracteres.")
    @Column(name = "cidade_patio")
    private String city;

    @NotBlank(message = "O estado não pode estar vazio.")
    @Size(min = 2, max = 2, message = "O estado deve ter 2 caracteres (UF).")
    @Column(name = "estado_patio")
    private String state;

    @NotNull(message = "A capacidade não pode ser nula.")
    @Min(value = 0, message = "A capacidade mínima é 0.")
    @Column(name = "capacidade_patio")
    private Integer capacity;
}
