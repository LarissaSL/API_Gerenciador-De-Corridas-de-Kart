package com.manascode.api_sgk.aplicacao.campeonato.validacoes;

import com.manascode.api_sgk.aplicacao.campeonato.AtualizarCampeonatoDTO;
import com.manascode.api_sgk.aplicacao.campeonato.CriarCampeonatoDTO;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CampeonatoException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ValidadorAnoDeCriacaoDoCampeonato implements IValidadorDeCampeonatos {
    @Override
    public void validar(CriarCampeonatoDTO dados) {
        verificar(dados.dataInicial());
    }

    @Override
    public void validar(AtualizarCampeonatoDTO dados) {
        if (dados.dataInicial() != null) {
            verificar(dados.dataInicial());
        }
    }

    public void verificar(LocalDate dataInicio) {
        int anoAtual = LocalDate.now().getYear();
        int anoDaDataInicio = dataInicio.getYear();

        if (anoDaDataInicio < anoAtual) {
            throw new CampeonatoException("Só é possivel registrar campeonatos a partir do ano atual " + anoAtual);
        }
    }
}
