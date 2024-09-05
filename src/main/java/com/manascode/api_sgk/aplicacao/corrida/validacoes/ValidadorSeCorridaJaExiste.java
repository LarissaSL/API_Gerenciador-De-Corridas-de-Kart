package com.manascode.api_sgk.aplicacao.corrida.validacoes;

import com.manascode.api_sgk.aplicacao.corrida.AtualizarCorridaDTO;
import com.manascode.api_sgk.aplicacao.corrida.CriarCorridaDTO;
import com.manascode.api_sgk.dominio.corrida.Classificacao;
import com.manascode.api_sgk.dominio.corrida.Corrida;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CorridaException;
import com.manascode.api_sgk.infraestrutura.persistencia.CampeonatoRepository;
import com.manascode.api_sgk.infraestrutura.persistencia.CorridaRepository;
import com.manascode.api_sgk.infraestrutura.persistencia.KartodromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class ValidadorSeCorridaJaExiste implements IValidadorCorrida {
    @Autowired
    private CorridaRepository repositorio;

    @Autowired
    private KartodromoRepository kartodromoRepository;

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    @Override
    public void validar(CriarCorridaDTO dados) {
        verificar(dados.nome(), dados.data(), dados.horario(), dados.classificacao(), dados.campeonatoId(), null);
    }

    @Override
    public void validar(AtualizarCorridaDTO dados) {
        Corrida corridaSalva = repositorio.findById(dados.id())
                .orElseThrow(() -> new CorridaException("Corrida não encontrada."));

        verificar(
                dados.nome() != null ? dados.nome() : corridaSalva.getNome(),
                dados.data() != null ? dados.data() : corridaSalva.getData(),
                dados.horario() != null ? dados.horario() : corridaSalva.getHorario(),
                dados.classificacao() != null ? dados.classificacao() : corridaSalva.getClassificacao(),
                dados.campeonatoId() != null ? dados.campeonatoId() : corridaSalva.getCampeonato().getId(),
                dados.id()
        );
    }

    private void verificar(String nome, LocalDate data, LocalTime horario, Classificacao classificacao, Long campeonatoId, Long idCorrida) {
        if (repositorio.existsByNomeAndDataAndHorarioAndClassificacaoAndCampeonatoIdAndNotId(
                nome, data, horario, classificacao, campeonatoId, idCorrida
        )) {
            throw new CorridaException("Já existe uma corrida com os mesmos dados fornecidos.");
        }
    }
}
