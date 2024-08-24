package com.manascode.api_sgk.aplicacao.usuario;

import java.time.LocalDate;

public record DetalharUsuarioDTO(Long id,
                                String nome,
                                String sobrenome,
                                LocalDate data_de_nascimento) {
}
