package com.manascode.api_sgk.aplicacao.campeonato.validacoes;

import com.manascode.api_sgk.aplicacao.campeonato.AtualizarCampeonatoDTO;
import com.manascode.api_sgk.aplicacao.campeonato.CriarCampeonatoDTO;
import com.manascode.api_sgk.dominio.campeonato.Campeonato;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CampeonatoException;
import com.manascode.api_sgk.infraestrutura.persistencia.CampeonatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ValidadorDataFinalDepoisDaDataInicial implements IValidadorDeCampeonatos{

    @Autowired
    private CampeonatoRepository repositorio;

    @Override
    public void validar(CriarCampeonatoDTO dados) {
        validarDataFinal(dados.dataInicial(), dados.dataFinal());
    }

    @Override
    public void validar(AtualizarCampeonatoDTO dados) {
        if (dados.dataInicial() != null && dados.dataFinal() != null) {
            validarDataFinal(dados.dataInicial(), dados.dataFinal());
        } else {
            Campeonato campeonatoSalvo = repositorio.getReferenceById(dados.id());

            LocalDate dataInicial = dados.dataInicial() != null ? dados.dataInicial() : campeonatoSalvo.getDataInicial();
            LocalDate dataFinal = dados.dataFinal() != null ? dados.dataFinal() : campeonatoSalvo.getDataFinal();

            validarDataFinal(dataInicial, dataFinal);
        }
    }

    public void validarDataFinal(LocalDate dataInicial, LocalDate dataFinal) {
        if (dataFinal.isBefore(dataInicial)) {
            throw new CampeonatoException("A data final do Campeonato deve ser no mesmo dia ou depois da data inicial.");
        }
    }
}
