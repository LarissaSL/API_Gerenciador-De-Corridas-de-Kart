package com.manascode.api_sgk.aplicacao.inscricao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manascode.api_sgk.dominio.inscricao.StatusPagamento;

public record ListarInscricaoParaCheckDTO(
        @JsonProperty("inscricao_id")
        Long idInscricao,
        DetalharInscricaoUsuarioDTO usuario,
        @JsonProperty("status_pagamento")
        StatusPagamento statusPagamento) {
}
