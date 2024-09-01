package com.manascode.api_sgk.aplicacao.campeonato;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AtualizarCampeonatoDTO(
        @NotNull
        Long id,
        String nome,
        String sigla,
        @JsonAlias("data_inicial")
        LocalDate dataInicial,
        @JsonAlias("data_final")
        LocalDate dataFinal) {
}
