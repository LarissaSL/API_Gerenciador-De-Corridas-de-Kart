package com.manascode.api_sgk.aplicacao.checkIn;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CriarCheckInDTO(
        @NotNull(message = "A Inscrição é obrigatória.")
        @JsonAlias("inscricao_id")
        Long idInscricao,

        @JsonAlias("peso_inicial")
        @DecimalMin(value = "0.0", inclusive = false, message = "O Peso Inicial deve ser maior que 0")
        @NotNull(message = "O Peso inicial é obrigatório.")
        BigDecimal pesoInicial,

        Integer lastro) {
}
