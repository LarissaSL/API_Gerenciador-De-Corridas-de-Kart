package com.manascode.api_sgk.aplicacao.campeonato;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record DetalharCampeonatoDTO(
        Long id,
        String nome,
        String sigla,

        @JsonProperty("data_inicial")
        LocalDate dataInicial,

        @JsonProperty("data_final")
        LocalDate dataFinal
) {
}
