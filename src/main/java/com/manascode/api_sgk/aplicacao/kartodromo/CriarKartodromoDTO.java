package com.manascode.api_sgk.aplicacao.kartodromo;

import com.manascode.api_sgk.dominio.kartodromo.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CriarKartodromoDTO(
        @NotBlank(message = "O nome é obrigatório")
        @Size(max = 45, message = "O Nome não pode passar de 45 caracteres.")
        String nome,

        @Valid
        Endereco endereco,

        @Size(max = 100, message = "O Endereço Foto não pode passar de 45 caracteres.")
        String endereco_foto
) {
}
