package com.manascode.api_sgk.aplicacao.checkIn.validacoes;

import com.manascode.api_sgk.aplicacao.checkIn.AtualizarCheckInDTO;
import com.manascode.api_sgk.aplicacao.checkIn.CriarCheckInDTO;
import com.manascode.api_sgk.dominio.corrida.Corrida;
import com.manascode.api_sgk.dominio.inscricao.Inscricao;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CheckException;
import com.manascode.api_sgk.infraestrutura.persistencia.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ValidadorDataDeCheckIn implements IValidadorCheckIn{
    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Override
    public void validar(CriarCheckInDTO dados) {
        verificar(dados.idInscricao());
    }

    @Override
    public void validar(AtualizarCheckInDTO dados) {

    }

    public void verificar (Long idInscricao) {
        Inscricao inscricao = inscricaoRepository.findById(idInscricao)
                .orElseThrow(() -> new CheckException("Inscrição não encontrada"));

        Corrida corrida = inscricao.getCorrida();
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataCorrida = corrida.getData();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataCorridaFormatada = dataCorrida.format(formatter);

        if (dataAtual.isAfter(dataCorrida)) {
            throw new CheckException("Não é possivel fazer Check-in depois da data da Corrida. Data da corrida: " + dataCorridaFormatada);
        }

        if (dataAtual.isBefore(dataCorrida)) {
            throw new CheckException("Não é possivel fazer Check-in antes da data da Corrida. Data da corrida: " + dataCorridaFormatada);
        }
    }
}
