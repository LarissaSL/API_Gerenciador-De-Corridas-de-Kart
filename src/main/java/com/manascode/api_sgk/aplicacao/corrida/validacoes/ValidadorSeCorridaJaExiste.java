package com.manascode.api_sgk.aplicacao.corrida.validacoes;

import com.manascode.api_sgk.aplicacao.corrida.AtualizarCorridaDTO;
import com.manascode.api_sgk.aplicacao.corrida.CriarCorridaDTO;
import com.manascode.api_sgk.dominio.corrida.Classificacao;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CorridaException;
import com.manascode.api_sgk.infraestrutura.persistencia.CorridaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class ValidadorSeCorridaJaExiste implements IValidadorCorrida{
    @Autowired
    private CorridaRepository repositorio;

    @Override
    public void validar(CriarCorridaDTO dados) {
        verificar(dados.nome(), dados.data(), dados.horario(), dados.classificacao(), dados.campeonato_id());
    }

    @Override
    public void validar(AtualizarCorridaDTO dados) {

    }

    public void verificar(String nome, LocalDate data, LocalTime horario, Classificacao classificacao, Long campeonatoId) {
        if (repositorio.existsByNomeAndDataAndHorarioAndClassificacaoAndCampeonatoId(nome, data, horario, classificacao, campeonatoId)) {
            throw new CorridaException("Essa corrida já está registrada no Sistema.");
        }
    }
}
