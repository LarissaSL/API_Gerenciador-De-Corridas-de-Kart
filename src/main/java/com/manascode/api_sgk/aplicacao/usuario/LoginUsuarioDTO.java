package com.manascode.api_sgk.aplicacao.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginUsuarioDTO(
        @Email(message = "Deve ser um endereço de e-mail válido.")
        @NotBlank(message = "E-mail é um campo obrigatório.")
        String email,

        @NotBlank(message = "Senha é um campo obrigatório.")
        String senha) {
}
