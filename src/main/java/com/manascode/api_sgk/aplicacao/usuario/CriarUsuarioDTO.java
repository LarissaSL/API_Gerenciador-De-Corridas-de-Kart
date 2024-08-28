package com.manascode.api_sgk.aplicacao.usuario;

import com.manascode.api_sgk.dominio.usuario.TipoUsuario;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CriarUsuarioDTO(
                @NotBlank(message = "Nome é um campo obrigatório.")
                String nome,

                @NotBlank(message = "Sobrenome é um campo obrigatório.")
                String sobrenome,

                @NotBlank(message = "CPF é um campo obrigatório.")
                @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos numéricos.")
                String cpf,

                @Pattern(regexp = "\\d{10,11}", message = "Telefone deve conter entre 10 e 11 dígitos numéricos.")
                String telefone,

                @NotNull(message = "Tipo de usuário é um campo obrigatório")
                TipoUsuario tipo,

                @NotBlank(message = "Email é um campo obrigatório.")
                @Email(message = "Email deve ser válido.")
                String email,

                @NotBlank(message = "Senha é um campo obrigatória.")
                @Size(min = 8, message = "Senha deve conter pelo menos 8 caracteres.")
                @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "Senha deve conter pelo menos uma letra maiúscula, uma letra minúscula e um número.")
                String senha,

                @NotNull(message = "Data de nascimento é um campo obrigatória.")
                LocalDate data_de_nascimento
) {
}
