package com.manascode.api_sgk.aplicacao.corrida;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.manascode.api_sgk.dominio.corrida.Classificacao;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record AtualizarCorridaDTO(
        @NotNull(message = "A corrida não pode ser nula.")
        Long id,

        @JsonAlias("campeonato_id")
        Long campeonatoId,
        @JsonAlias("kartodromo_id")
        Long kartodromoId,

        @Size(max = 45, message = "O Nome não pode passar de 45 caracteres.")
        String nome,

        LocalDate data,
        LocalTime horario,
        Boolean transmissao,
        String categoria,
        Classificacao classificacao,
        String codigo,

        @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que 0")
        BigDecimal preco) {
}
