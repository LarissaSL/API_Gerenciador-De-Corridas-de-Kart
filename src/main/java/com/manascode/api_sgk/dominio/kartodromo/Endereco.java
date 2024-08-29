package com.manascode.api_sgk.dominio.kartodromo;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @NotBlank(message = "Rua é um campo obrigatório.")
    @Size(max = 45, message = "Rua não pode passar de 45 caracteres.")
    private String rua;

    @NotBlank(message = "Número é um campo obrigatório.")
    @Size(max = 10, message = "Número não pode passar de 10 caracteres.")
    private String numero;

    @NotBlank(message = "Bairro é um campo obrigatório.")
    @Size(max = 45, message = "Bairro não pode passar de 45 caracteres.")
    private String bairro;

    @Pattern(regexp = "\\d{8}", message = "Digite apenas 8 dígitos.")
    @NotNull(message = "CEP é um campo obrigatório.")
    private String cep;

    @Size(max = 45, message = "Cidade não pode passar de 45 caracteres.")
    private String cidade;

    @Size(max = 45, message = "Estado não pode passar de 45 caracteres.")
    private String estado;
}
