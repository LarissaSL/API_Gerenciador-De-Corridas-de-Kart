package com.manascode.api_sgk.aplicacao.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginUsuarioDTO(
        @Email
        @NotBlank
        String email,

        @NotBlank
        String senha) {
}
