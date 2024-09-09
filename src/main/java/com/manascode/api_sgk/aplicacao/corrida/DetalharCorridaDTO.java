package com.manascode.api_sgk.aplicacao.corrida;

import com.manascode.api_sgk.dominio.corrida.Classificacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record DetalharCorridaDTO(
        Long id,
        DetalharCorridaCampeonatoDTO campeonato,
        DetalharCorridaKartodromoDTO kartodromo,
        String nome,
        LocalDate data,
        LocalTime horario,
        Boolean transmissao,
        String categoria,
        Classificacao classificacao,
        String codigo,
        BigDecimal preco) {
}
