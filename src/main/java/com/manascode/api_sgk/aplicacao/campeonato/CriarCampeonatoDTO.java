package com.manascode.api_sgk.aplicacao.campeonato;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CriarCampeonatoDTO(
        @NotBlank(message = "Nome é um campo obrigatório.")
        @Size(max = 45, message = "Nome não pode passar de 45 caracteres")
        String nome,

        @NotNull(message = "Data inicial é um campo obrigatório.")
        @JsonAlias("data_inicial")
        LocalDate dataInicial,

        @NotNull(message = "Data final é um campo obrigatório.")
        @JsonAlias("data_final")
        LocalDate dataFinal) {
}
