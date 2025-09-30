package br.com.fiap.iottu.user;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_USUARIO")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;

    @Column(name = "nome_usuario")
    private String nome;
    @Column(name = "email_usuario")
    private String email;
    @Column(name = "senha_usuario")
    private String senha;

    @Column(name = "role")
    private String role;
}