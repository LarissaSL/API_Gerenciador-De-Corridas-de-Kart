package com.manascode.api_sgk.aplicacao.inscricao.validacoes;

import com.manascode.api_sgk.aplicacao.inscricao.AtualizarInscricaoDTO;
import com.manascode.api_sgk.aplicacao.inscricao.CriarInscricaoDTO;

public interface IValidadorInscricao {
    void validar(CriarInscricaoDTO dados);
    void validar(AtualizarInscricaoDTO dados);
}
