package com.manascode.api_sgk.aplicacao.inscricao.validacoes;

import com.manascode.api_sgk.aplicacao.inscricao.AtualizarInscricaoDTO;
import com.manascode.api_sgk.aplicacao.inscricao.CriarInscricaoDTO;
import com.manascode.api_sgk.dominio.corrida.Corrida;
import com.manascode.api_sgk.dominio.inscricao.Inscricao;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.InscricaoException;
import com.manascode.api_sgk.infraestrutura.persistencia.CorridaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ValidadorDataDeInscricaoAntesOuNoDiaDaDataDaCorrida implements IValidadorInscricao {
    @Autowired
    private CorridaRepository repositorio;

    @Override
    public void validar(CriarInscricaoDTO dados) {
        verificar(dados.corridaId());
    }

    @Override
    public void validar(AtualizarInscricaoDTO dados) {
        if (dados.corridaId() != null) {
            verificar(dados.corridaId());
        }
    }

    public void verificar(Long idCorrida) {
        Corrida corrida = repositorio.getReferenceById(idCorrida);

        LocalDate dataAtual = LocalDate.now();
        LocalDate dataCorrida = corrida.getData();

        if (dataAtual.isAfter(dataCorrida)) {
            throw new InscricaoException("A inscrição não é permitida para corridas que já ocorreram.");
        }
    }
}
