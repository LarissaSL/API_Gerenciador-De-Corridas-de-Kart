package com.manascode.api_sgk.aplicacao.corrida.validacoes;

import com.manascode.api_sgk.aplicacao.corrida.AtualizarCorridaDTO;
import com.manascode.api_sgk.aplicacao.corrida.CriarCorridaDTO;
import com.manascode.api_sgk.dominio.campeonato.Campeonato;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CorridaException;
import com.manascode.api_sgk.infraestrutura.persistencia.CampeonatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorChaveEstrageiraCampeonato implements IValidadorCorrida{

    @Autowired
    private CampeonatoRepository repositorio;

    @Override
    public void validar(CriarCorridaDTO dados) {
        verificar(dados.campeonatoId());
    }

    @Override
    public void validar(AtualizarCorridaDTO dados) {
        if (dados.campeonatoId() != null) {
            verificar(dados.campeonatoId());
        }
    }

    public void verificar(Long idCampeonato) {
        Campeonato campeonato = repositorio.getReferenceById(idCampeonato);

        if (campeonato == null) {
            throw new CorridaException("Campeonato não existe no sistema.");
        }

        if (!campeonato.getAtivo()) {
            throw new CorridaException("Campeonato precisa estar ativo no sistema.");
        }
    }
}
