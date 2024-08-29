package com.manascode.api_sgk.aplicacao.kartodromo;

import com.manascode.api_sgk.dominio.kartodromo.Endereco;

public record DetalharKartodromoDTO(Long id,
                                    String nome,
                                    Endereco endereco,
                                    String endereco_foto) {
}
