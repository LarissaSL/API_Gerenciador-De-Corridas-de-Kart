package com.manascode.api_sgk.aplicacao.kartodromo;

import com.manascode.api_sgk.dominio.kartodromo.Endereco;

public record AtualizarKartodromoDTO(
        Long id,
        String nome,
        Endereco endereco,
        String endereco_foto) {
}
