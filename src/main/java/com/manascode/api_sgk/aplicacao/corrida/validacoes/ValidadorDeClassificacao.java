package com.manascode.api_sgk.aplicacao.corrida.validacoes;

import com.manascode.api_sgk.aplicacao.corrida.AtualizarCorridaDTO;
import com.manascode.api_sgk.aplicacao.corrida.CriarCorridaDTO;
import com.manascode.api_sgk.dominio.campeonato.Campeonato;
import com.manascode.api_sgk.dominio.corrida.Classificacao;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CorridaException;
import com.manascode.api_sgk.infraestrutura.persistencia.CampeonatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorDeClassificacao implements IValidadorCorrida{

    @Autowired
    private CampeonatoRepository repositorio;

    @Override
    public void validar(CriarCorridaDTO dados) {
        verificar(dados.campeonato_id(), dados.classificacao());
    }

    @Override
    public void validar(AtualizarCorridaDTO dados) {

    }

    public void verificar(Long idCampeonato, Classificacao classificacaoCorrida) {
        Campeonato campeonato = repositorio.getReferenceById(idCampeonato);
        String siglaCampeonato = campeonato.getSigla();

        // Definindo se a classificação esta certa pela sigla do campeonato, que é determinada pelo nome do mesmo.
        if ("CKC".equalsIgnoreCase(siglaCampeonato) && classificacaoCorrida.equals(Classificacao.DDL_90)) {
            throw new CorridaException("Corridas do CKC só podem ter a classificação 110 e 95.");
        }

        if ("DDL".equalsIgnoreCase(siglaCampeonato) && !classificacaoCorrida.equals(Classificacao.DDL_90)) {
            throw new CorridaException("Corridas do DDL só podem ter a classificação 90.");
        }
    }
}
