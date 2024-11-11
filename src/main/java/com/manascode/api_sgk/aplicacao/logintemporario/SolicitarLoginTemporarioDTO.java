package com.manascode.api_sgk.aplicacao.logintemporario;

import jakarta.validation.constraints.*;

public record SolicitarLoginTemporarioDTO(
        @Email(message = "Precisa ser um email valido.")
        @NotBlank(message = "Email do usuário é obrigatório") String email,

        @NotBlank(message = "CPF é um campo obrigatório.")
        @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos numéricos.")
        String cpf
) {
}
