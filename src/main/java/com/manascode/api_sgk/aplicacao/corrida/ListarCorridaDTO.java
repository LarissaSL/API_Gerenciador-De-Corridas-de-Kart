package com.manascode.api_sgk.aplicacao.corrida;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manascode.api_sgk.dominio.corrida.Classificacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record ListarCorridaDTO(Long id,
                               @JsonProperty("campeonato_nome")
                               String campeonatoNome,

                               @JsonProperty("kartodromo_nome")
                               String kartodromoNome,
                               String nome,
                               LocalDate data,
                               LocalTime horario,
                               Boolean transmissao,
                               String categoria,
                               Classificacao classificacao,
                               String codigo,
                               BigDecimal preco) {
}