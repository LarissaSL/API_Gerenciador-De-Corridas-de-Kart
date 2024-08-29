package com.manascode.api_sgk.aplicacao.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AtualizarUsuarioDTO(
        @NotNull
        Long id,

        @Size(max = 45, message = "Nome não pode passar de 45 caracteres")
        String nome,

        @Size(max = 45, message = "Sobrenome não pode passar de 45 caracteres")
        String sobrenome,

        @Pattern(regexp = "\\d{10,11}", message = "Telefone deve conter entre 10 e 11 dígitos numéricos.")
        String telefone,

        @Email(message = "Email deve ser válido.")
        String email,

        @Size(min = 8 , message = "Senha deve conter pelo menos 8 caracteres.")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "Senha deve conter pelo menos uma letra maiúscula, uma letra minúscula e um número.")
        String senha,

        LocalDate data_de_nascimento,

        @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos numéricos.")
        String cpf

) {
}
