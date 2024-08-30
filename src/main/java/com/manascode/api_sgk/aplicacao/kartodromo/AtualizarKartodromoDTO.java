package com.manascode.api_sgk.aplicacao.kartodromo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.manascode.api_sgk.dominio.kartodromo.Endereco;

public record AtualizarKartodromoDTO(
        Long id,
        String nome,
        Endereco endereco,

        @JsonAlias("endereco_foto")
        String enderecoFoto) {
}
