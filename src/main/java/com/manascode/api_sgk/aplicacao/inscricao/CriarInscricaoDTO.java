package com.manascode.api_sgk.aplicacao.inscricao;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.manascode.api_sgk.dominio.inscricao.StatusPagamento;
import jakarta.validation.constraints.NotNull;

public record CriarInscricaoDTO(
        @NotNull(message = "A Corrida é obrigatória.")
        @JsonAlias("corrida_id")
        Long corridaId,

        @NotNull(message = "O Usuário é obrigatório.")
        @JsonAlias("usuario_id")
        Long usuarioId,

        @JsonAlias("status_pgto")
        StatusPagamento statusPagamento

) {
}
