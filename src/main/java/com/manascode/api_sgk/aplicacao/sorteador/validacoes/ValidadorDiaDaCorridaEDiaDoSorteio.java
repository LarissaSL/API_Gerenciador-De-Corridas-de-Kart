package com.manascode.api_sgk.aplicacao.sorteador.validacoes;

import com.manascode.api_sgk.dominio.corrida.Corrida;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.SorteioException;
import com.manascode.api_sgk.infraestrutura.persistencia.CorridaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ValidadorDiaDaCorridaEDiaDoSorteio implements IValidadorSorteio{

    @Autowired
    private CorridaRepository repositorio;

    @Override
    public void validar(Long idCorrida) {
        Corrida corrida = repositorio.getReferenceById(idCorrida);

        LocalDate dataCorrida = corrida.getData();
        LocalDate dataAtual = LocalDate.now();

        // Formata a data para o padrão dd/MM/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataCorridaFormatada = dataCorrida.format(formatter);

        if (!dataCorrida.isEqual(dataAtual)) {
            throw new SorteioException("Só é permitido fazer o Sorteio no mesmo dia da Corrida. Dia da Corrida: " + dataCorridaFormatada);
        }
    }
}
