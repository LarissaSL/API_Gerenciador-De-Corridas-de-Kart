package com.manascode.api_sgk.aplicacao.campeonato.validacoes;

import com.manascode.api_sgk.aplicacao.campeonato.AtualizarCampeonatoDTO;
import com.manascode.api_sgk.aplicacao.campeonato.CriarCampeonatoDTO;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CampeonatoException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ValidadorDataFinalDepoisDaDataInicial implements IValidadorDeCampeonatos{
    @Override
    public void validar(CriarCampeonatoDTO dados) {
        validarDataFinal(dados.dataInicial(), dados.dataFinal());
    }

    @Override
    public void validar(AtualizarCampeonatoDTO dados) {
        return;
    }

    public void validarDataFinal(LocalDate dataInicial, LocalDate dataFinal) {
        if (dataFinal.isBefore(dataInicial)) {
            throw new CampeonatoException("A data final do Campeonato deve ser no mesmo dia ou depois da data inicial.");
        }
    }
}
