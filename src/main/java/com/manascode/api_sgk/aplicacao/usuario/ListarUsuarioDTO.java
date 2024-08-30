package com.manascode.api_sgk.aplicacao.usuario;

import com.manascode.api_sgk.dominio.usuario.TipoUsuario;

import java.time.LocalDate;

public record ListarUsuarioDTO(
        Long id,
        String nome,
        String sobrenome,

        String cpf,

        String telefone,

        TipoUsuario tipo,

        String email,
        String senha,
        LocalDate data_de_nascimento) {
}
