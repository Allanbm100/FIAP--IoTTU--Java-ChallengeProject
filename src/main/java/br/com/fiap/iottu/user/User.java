package br.com.fiap.iottu.user;

import br.com.fiap.iottu.validation.OnUpdate;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_USUARIO")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    @NotNull(groups = OnUpdate.class, message = "O ID do usuário é obrigatório na atualização.")
    private Integer id;

    @NotBlank(message = "O nome não pode estar vazio.")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
    @Column(name = "nome_usuario")
    private String name;

    @NotBlank(message = "O e-mail não pode estar vazio.")
    @Email(message = "Formato de e-mail inválido.")
    @Size(min = 5, max = 100, message = "O e-mail deve ter entre 5 e 100 caracteres.")
    @Column(name = "email_usuario")
    private String email;

    @NotBlank(message = "A senha não pode estar vazia.")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
    @Column(name = "senha_usuario")
    private String password;

    @NotBlank(message = "O perfil não pode estar vazio.", groups = OnUpdate.class)
    @Column(name = "role")
    private String role;

}
