package com.manascode.api_sgk.aplicacao.check;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensagemWhatsappService {

    public String gerarMensagem(List<CompartilharCheckInProjecao> checkins, String nomeCorrida, String categoria, String classificacao) {
        // Formatar o cabeçalho
        String header = String.format("*%s | %s %s*\n\n", nomeCorrida, categoria, classificacao);

        // Formatar a lista de pilotos e números de kart
        StringBuilder tabela = new StringBuilder();
        checkins.forEach(checkin -> {
            tabela.append(String.format("Piloto: %s %s\nNúmero Kart: %d\n\n", checkin.getNome(), checkin.getSobrenome(), checkin.getNumeroDoKart()));
        });

        // Adicionar o total de pilotos
        String totalPilotos = String.format("Total de Pilotos: %d", checkins.size());

        return header + tabela.toString() + totalPilotos;
    }
}

