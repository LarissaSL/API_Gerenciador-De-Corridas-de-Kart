package com.manascode.api_sgk.aplicacao.corrida;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.manascode.api_sgk.dominio.corrida.Classificacao;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record CriarCorridaDTO(
        @NotNull(message = "O Campeonato é obrigatório.")
        @JsonAlias("campeonato_id")
        Long campeonatoId,
        @NotNull(message = "O Kartodromo é obrigatório.")
        @JsonAlias("kartodromo_id")
        Long kartodromoId,

        @NotBlank(message = "O Nome é obrigatório.")
        @Size(max = 45, message = "O Nome não pode passar de 45 caracteres.")
        String nome,

        @NotNull(message = "A data é obrigatória.")
        LocalDate data,

        @NotNull(message = "O horário é obrigatório.")
        LocalTime horario,
        Boolean transmissao,
        String categoria,

        @NotNull(message = "A classificação é obrigatória.")
        Classificacao classificacao,
        String codigo,

        @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que 0")
        @NotNull(message = "O Preço é obrigatório.")
        BigDecimal preco
) {
}
