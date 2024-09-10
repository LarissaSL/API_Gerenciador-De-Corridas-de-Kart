package com.manascode.api_sgk.aplicacao.inscricao;

import com.manascode.api_sgk.dominio.corrida.Classificacao;

import java.time.LocalDate;
import java.time.LocalTime;

public record DetalharInscricaoCorridaDTO(Long id,
                                          String nome,
                                          String campeonato,
                                          Classificacao classificacao,
                                          String sigla,
                                          LocalTime horario,
                                          LocalDate data
                                          ) {
}
