package com.manascode.api_sgk.aplicacao.kartodromo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.manascode.api_sgk.dominio.kartodromo.Endereco;
import jakarta.validation.constraints.NotNull;

public record AtualizarKartodromoDTO(
        @NotNull (message = "O Kartodromo não pode ser nulo.")
        Long id,
        String nome,
        Endereco endereco,

        @JsonAlias("endereco_foto")
        String enderecoFoto) {
}
