package com.manascode.api_sgk.aplicacao.checkIn.validacoes;

import com.manascode.api_sgk.aplicacao.checkIn.AtualizarCheckInDTO;
import com.manascode.api_sgk.aplicacao.checkIn.CriarCheckInDTO;

public interface IValidadorCheckIn {
    void validar(CriarCheckInDTO dados);
    void validar(AtualizarCheckInDTO dados);
}
