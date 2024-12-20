package com.manascode.api_sgk.aplicacao.sorteador;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record DetalharInformacaoGeralDoSorteioDTO(
        @JsonProperty("total_de_numeros_sorteados")
        Integer totalDeNumerosSorteados,

        @JsonProperty("lista_de_numeros_fora_do_sorteio")
        List<Integer> listaNumerosForaDoSorteio,

        @JsonProperty("lista_de_numeros_restantes")
        List<Integer> listaNumerosRestantes,

        List<DetalharInformacaoPilotoDoSorteioDTO> pilotos) {
}
