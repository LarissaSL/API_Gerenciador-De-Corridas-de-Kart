package com.manascode.api_sgk.aplicacao.inscricao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manascode.api_sgk.dominio.inscricao.StatusPagamento;

import java.time.LocalDateTime;

public record DetalharInscricaoDTO(Long id,
                                   DetalharInscricaoUsuarioDTO usuario,
                                   DetalharInscricaoCorridaDTO corrida,

                                   @JsonProperty("status_pagamento")
                                   StatusPagamento statusPagamento,

                                   @JsonProperty("data_inscricao")
                                   LocalDateTime dataInscricao
) {
}
