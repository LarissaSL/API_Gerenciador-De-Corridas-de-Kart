package com.manascode.api_sgk.aplicacao.checkOut;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RealizarCheckOutDTO(@NotNull(message = "Id de Inscricao/Check-in é um campo obrigatório.")
                                  @JsonAlias("id_inscricao")
                                  Long idInscricao,

                                  @JsonAlias("peso_final")
                                  @DecimalMin(value = "0.0", inclusive = false, message = "O Peso Final deve ser maior que 0")
                                  BigDecimal pesoFinal,

                                  Boolean classificado) {
}
