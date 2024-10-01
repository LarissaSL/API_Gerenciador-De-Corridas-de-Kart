package com.manascode.api_sgk.aplicacao.checkOut.validacoes;

import com.manascode.api_sgk.aplicacao.checkOut.RealizarCheckOutDTO;

public interface IValidadorCheckOut {
    void validar(RealizarCheckOutDTO dados);
}
