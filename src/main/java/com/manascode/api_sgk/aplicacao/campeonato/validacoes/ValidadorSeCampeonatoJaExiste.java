package com.manascode.api_sgk.aplicacao.campeonato.validacoes;

import com.manascode.api_sgk.aplicacao.campeonato.AtualizarCampeonatoDTO;
import com.manascode.api_sgk.aplicacao.campeonato.CriarCampeonatoDTO;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CampeonatoException;
import com.manascode.api_sgk.infraestrutura.persistencia.CampeonatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ValidadorSeCampeonatoJaExiste implements IValidadorDeCampeonatos{

    @Autowired
    private CampeonatoRepository repositorio;

    @Override
    public void validar(CriarCampeonatoDTO dados) {
        validarCampeonato(dados.nome(), dados.dataInicial(), dados.dataFinal());
    }

    @Override
    public void validar(AtualizarCampeonatoDTO dados) {

    }

    public void validarCampeonato(String nome, LocalDate dataInicio, LocalDate dataFinal) {
        boolean jaExisteCampeonato = repositorio.existsByNomeAndDataInicialAndDataFinal(nome, dataInicio, dataFinal);

        if (jaExisteCampeonato) {
            throw new CampeonatoException("Esse campeonato já está registrado no Sistema.");
        }
    }
}
