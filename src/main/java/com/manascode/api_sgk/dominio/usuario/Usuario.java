package com.manascode.api_sgk.dominio.usuario;

import com.manascode.api_sgk.aplicacao.usuario.AtualizarUsuarioDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sobrenome;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String telefone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuario tipo = TipoUsuario.usuario;

    @Column(unique = true)
    private String email;

    private String senha;
    private LocalDate data_de_nascimento;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean ativo = true;

    public void excluir() {
        this.ativo = false;
    }

    public void atualizar(AtualizarUsuarioDTO dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.sobrenome() != null) {
            this.sobrenome = dados.sobrenome();
        }

        if (dados.senha() != null) {
            this.senha = dados.senha();
        }

        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }

        if (dados.email() != null) {
            this.email = dados.email();
        }

        if (dados.cpf() != null) {
            this.cpf = dados.cpf();
        }

        if (dados.data_de_nascimento() != null) {
            this.data_de_nascimento = dados.data_de_nascimento();
        }
    }
}
