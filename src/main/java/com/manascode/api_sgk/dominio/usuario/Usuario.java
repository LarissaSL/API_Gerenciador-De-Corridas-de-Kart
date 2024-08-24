package com.manascode.api_sgk.dominio.usuario;

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

    @Column(length = 45)
    private String nome;

    @Column(length = 45)
    private String sobrenome;

    @Column(unique = true, length = 11)
    private String cpf;

    @Column(unique = true, length = 11)
    private String telefone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuario tipo = TipoUsuario.usuario;

    @Column(unique = true, length = 45)
    private String email;

    @Column(length = 45)
    private String senha;
    private LocalDate data_de_nascimento;
    private boolean ativo = true;

    public void excluir() {
        this.ativo = false;
    }
}
