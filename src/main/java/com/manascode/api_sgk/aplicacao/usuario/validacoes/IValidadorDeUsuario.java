package com.manascode.api_sgk.aplicacao.usuario.validacoes;

import com.manascode.api_sgk.aplicacao.usuario.AtualizarUsuarioDTO;
import com.manascode.api_sgk.aplicacao.usuario.CriarUsuarioDTO;

public interface IValidadorDeUsuario {
    void validar(CriarUsuarioDTO dados);
    void validar(AtualizarUsuarioDTO dados);
}
