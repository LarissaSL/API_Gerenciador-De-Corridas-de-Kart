package com.manascode.api_sgk.aplicacao.usuario;

import java.time.LocalDate;

public record DetalharUsuarioDTO(Long id,
                                String nome,
                                String sobrenome,
                                String email,
                                String telefone,
                                LocalDate data_de_nascimento) {
}
