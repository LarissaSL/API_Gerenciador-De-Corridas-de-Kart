package com.manascode.api_sgk.aplicacao.usuario;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.manascode.api_sgk.dominio.usuario.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CriarUsuarioDTO(
                @NotBlank(message = "Nome é um campo obrigatório.")
                @Size(max = 45, message = "Nome não pode passar de 45 caracteres")
                String nome,

                @NotBlank(message = "Sobrenome é um campo obrigatório.")
                @Size(max = 45, message = "Sobrenome não pode passar de 45 caracteres")
                String sobrenome,

                @NotBlank(message = "CPF é um campo obrigatório.")
                @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos numéricos.")
                String cpf,

                @Pattern(regexp = "\\d{10,11}", message = "Telefone deve conter entre 10 e 11 dígitos numéricos.")
                String telefone,

                @NotNull(message = "Tipo de usuário é um campo obrigatório.")
                TipoUsuario tipo,

                @NotBlank(message = "Email é um campo obrigatório.")
                @Email(message = "Email deve ser válido.")
                String email,

                @NotBlank(message = "Senha é um campo obrigatório.")
                @Size(min = 8, message = "Senha deve conter pelo menos 8 caracteres.")
                @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "Senha deve conter pelo menos uma letra maiúscula, uma letra minúscula e um número.")
                String senha,

                @NotNull(message = "Data de nascimento é um campo obrigatório.")
                @JsonAlias("data_de_nascimento")
                LocalDate dataDeNascimento
) {
}
