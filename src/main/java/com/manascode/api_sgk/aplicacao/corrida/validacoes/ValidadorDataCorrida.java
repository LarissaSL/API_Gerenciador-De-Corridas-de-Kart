package com.manascode.api_sgk.aplicacao.corrida.validacoes;

import com.manascode.api_sgk.aplicacao.corrida.AtualizarCorridaDTO;
import com.manascode.api_sgk.aplicacao.corrida.CriarCorridaDTO;
import com.manascode.api_sgk.dominio.campeonato.Campeonato;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CorridaException;
import com.manascode.api_sgk.infraestrutura.persistencia.CampeonatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ValidadorDataCorrida implements IValidadorCorrida{

    @Autowired
    private CampeonatoRepository repositorio;

    @Override
    public void validar(CriarCorridaDTO dados) {
        verificar(dados.campeonato_id(), dados.data());
    }

    @Override
    public void validar(AtualizarCorridaDTO dados) {

    }

    public void verificar (Long idCampeonato, LocalDate dataCorrida) {
        Campeonato campeonato = repositorio.getReferenceById(idCampeonato);

        LocalDate dataInicialCampeonato = campeonato.getDataInicial();
        LocalDate dataFinalCampeonato = campeonato.getDataFinal();

        if (dataCorrida.isBefore(dataInicialCampeonato)) {
            throw new CorridaException("A data da corrida precisa ser depois da data inicial ou no mesmo dia de inicio do campeonato.");
        }

        if (dataCorrida.isAfter(dataFinalCampeonato)) {
            throw new CorridaException("A data de corrida precisa ser antes da data final ou no mesmo dia de encerramento do campeonato.");
        }
    }
}
