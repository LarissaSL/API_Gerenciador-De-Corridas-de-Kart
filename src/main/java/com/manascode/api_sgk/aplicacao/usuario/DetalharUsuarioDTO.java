package com.manascode.api_sgk.aplicacao.usuario;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record DetalharUsuarioDTO(Long id,
                                 String nome,
                                 String sobrenome,
                                 String email,
                                 String telefone,
                                 @JsonProperty("data_de_nascimento")
                                 LocalDate dataDeNascimento) {
}
