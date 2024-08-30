package com.manascode.api_sgk.aplicacao.kartodromo;

import com.manascode.api_sgk.dominio.kartodromo.Endereco;

public record ListarKartodromoDTO(
        Long id,
        String nome,
        Endereco endereco,
        String endereco_foto) {
}
