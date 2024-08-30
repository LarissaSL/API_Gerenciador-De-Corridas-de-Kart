package com.manascode.api_sgk.aplicacao.kartodromo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manascode.api_sgk.dominio.kartodromo.Endereco;

public record ListarKartodromoDTO(
        Long id,
        String nome,
        Endereco endereco,
        @JsonProperty("endereco_foto")
        String enderecoFoto) {
}
