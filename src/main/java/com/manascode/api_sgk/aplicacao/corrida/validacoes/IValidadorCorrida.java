package com.manascode.api_sgk.aplicacao.corrida.validacoes;

import com.manascode.api_sgk.aplicacao.corrida.AtualizarCorridaDTO;
import com.manascode.api_sgk.aplicacao.corrida.CriarCorridaDTO;

public interface IValidadorCorrida {
    void validar(CriarCorridaDTO dados);
    void validar(AtualizarCorridaDTO dados);
}
