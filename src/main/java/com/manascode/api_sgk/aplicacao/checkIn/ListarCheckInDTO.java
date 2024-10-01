package com.manascode.api_sgk.aplicacao.checkIn;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manascode.api_sgk.aplicacao.inscricao.ListarInscricaoDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ListarCheckInDTO(
        Long id,
        @JsonProperty("peso_inicial")
        BigDecimal pesoInicial,

        @JsonProperty("peso_final")
        BigDecimal pesoFinal,

        @JsonProperty("numero_do_kart")
        Integer numeroDoKart,

        Integer lastro,
        Boolean classificado,

        @JsonProperty("data_do_checkIn")
        LocalDateTime dataDoCheckIn,
        ListarInscricaoDTO inscricao) {
}
