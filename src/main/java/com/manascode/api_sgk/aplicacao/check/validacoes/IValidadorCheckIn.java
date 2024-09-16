package com.manascode.api_sgk.aplicacao.check.validacoes;

import com.manascode.api_sgk.aplicacao.check.AtualizarCheckInDTO;
import com.manascode.api_sgk.aplicacao.check.CriarCheckInDTO;

public interface IValidadorCheckIn {
    void validar(CriarCheckInDTO dados);
    void validar(AtualizarCheckInDTO dados);
}
