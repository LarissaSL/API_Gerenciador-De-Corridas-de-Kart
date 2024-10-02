package com.manascode.api_sgk.aplicacao.sorteador;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DetalharInformacaoPilotoDoSorteioDTO(String nome,
                                                   String sobrenome,
                                                   @JsonProperty("numero_do_kart")
                                                   Integer numeroDoKart) {
}
