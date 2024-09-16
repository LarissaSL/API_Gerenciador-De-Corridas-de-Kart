package com.manascode.api_sgk.aplicacao.inscricao;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.manascode.api_sgk.dominio.inscricao.StatusPagamento;
import jakarta.validation.constraints.NotNull;

public record AtualizarInscricaoDTO(
        @NotNull
        Long id,
        @JsonAlias("corrida_id")
        Long corridaId,

        @JsonAlias("usuario_id")
        Long usuarioId,

        @JsonAlias("status_pagamento")
        StatusPagamento statusPagamento) {
}
