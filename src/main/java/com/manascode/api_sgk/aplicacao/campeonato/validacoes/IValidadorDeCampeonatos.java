package com.manascode.api_sgk.aplicacao.campeonato.validacoes;

import com.manascode.api_sgk.aplicacao.campeonato.AtualizarCampeonatoDTO;
import com.manascode.api_sgk.aplicacao.campeonato.CriarCampeonatoDTO;

public interface IValidadorDeCampeonatos {
    void validar(CriarCampeonatoDTO dados);
    void validar(AtualizarCampeonatoDTO dados);
}
