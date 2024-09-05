package com.manascode.api_sgk.aplicacao.corrida.validacoes;

import com.manascode.api_sgk.aplicacao.corrida.AtualizarCorridaDTO;
import com.manascode.api_sgk.aplicacao.corrida.CriarCorridaDTO;
import com.manascode.api_sgk.dominio.corrida.Corrida;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CorridaException;
import com.manascode.api_sgk.infraestrutura.persistencia.CorridaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class ValidadorSeJaExisteDuasCorridasNoMesmoHorarioEDia implements IValidadorCorrida{
    @Autowired
    private CorridaRepository repositorio;

    @Override
    public void validar(CriarCorridaDTO dados) {
        verificar(dados.data(), dados.horario());
    }

    @Override
    public void validar(AtualizarCorridaDTO dados) {
        if (dados.horario() != null || dados.data() != null) {
            Corrida corrida = repositorio.getReferenceById(dados.id());

            LocalDate data = dados.data() != null ? dados.data() : corrida.getData();
            LocalTime horario = dados.horario() != null ? dados.horario() : corrida.getHorario();

            verificar(data, horario);
        }
    }

    public void verificar(LocalDate dataCorrida, LocalTime horarioCorrida) {
        int quantidadeDeCorridasNoDiaEHorario = repositorio.countByDataAndHorario(dataCorrida, horarioCorrida);

        if (quantidadeDeCorridasNoDiaEHorario >= 2) {
            throw new CorridaException("Já tem duas corridas acontecendo no mesmo dia e horário");
        }
    }
}
