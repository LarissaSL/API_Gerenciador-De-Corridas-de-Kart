package com.manascode.api_sgk.aplicacao.sorteador;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record ConfiguracoesDoSorteioDTO(@NotNull(message = "Corrida é um campo obrigatório.")
                                        @JsonProperty("id_corrida")
                                        Long idCorrida,

                                        @NotNull(message = "Maior número de Kart é um campo obrigatório.")
                                        @Positive(message = "O maior número de kart deve ser maior que 0")
                                        @JsonProperty("maior_numero_de_kart")
                                        Integer maiorNumeroDeKart,

                                        @NotNull(message = "A Lista de números fora do Sorteio é um campo obrigatório.")
                                        @JsonProperty("numeros_fora_do_sorteio")
                                        List<Integer> numerosForaDoSorteio) {
}
