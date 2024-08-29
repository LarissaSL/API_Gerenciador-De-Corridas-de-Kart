package com.manascode.api_sgk.dominio.kartodromo;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String rua;
    private String numero;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
}
