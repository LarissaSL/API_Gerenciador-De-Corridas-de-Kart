package com.manascode.api_sgk.aplicacao.check.validacoes;

import com.manascode.api_sgk.aplicacao.check.AtualizarCheckInDTO;
import com.manascode.api_sgk.aplicacao.check.CriarCheckInDTO;
import com.manascode.api_sgk.dominio.check.Check;
import com.manascode.api_sgk.dominio.corrida.Corrida;
import com.manascode.api_sgk.dominio.inscricao.Inscricao;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CheckException;
import com.manascode.api_sgk.infraestrutura.persistencia.CheckRepository;
import com.manascode.api_sgk.infraestrutura.persistencia.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidadorPeso implements IValidadorCheckIn{

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private CheckRepository repositorio;

    @Override
    public void validar(CriarCheckInDTO dados) {
        verificar(dados.idInscricao(), dados.pesoInicial(), dados.lastro());
    }

    @Override
    public void validar(AtualizarCheckInDTO dados) {
        Check checkInSalvo = repositorio.encontrarPorInscricao(dados.idInscricao());

        verificar(dados.idInscricao(),
                dados.pesoInicial() != null ? dados.pesoInicial() : checkInSalvo.getPesoInicial(),
                dados.lastro() != null ? dados.lastro() : checkInSalvo.getLastro());
    }

    public void verificar(Long idInscricao, BigDecimal peso, Integer lastro) {
        // Recuperar a inscrição pelo ID
        Inscricao inscricao = inscricaoRepository.findById(idInscricao)
                .orElseThrow(() -> new CheckException("Inscrição não encontrada"));

        // Recuperar a corrida associada à inscrição
        Corrida corrida = inscricao.getCorrida();

        // Obter a classificação da corrida
        String classificacao = corrida.getClassificacao().name();

        // Definir o padrão para extrair o número após o "_"
        Pattern pattern = Pattern.compile(".*_(\\d+)$");
        Matcher matcher = pattern.matcher(classificacao);

        if (matcher.find()) {
            // Extrair o número da classificação
            int pesoParaCorrida = Integer.parseInt(matcher.group(1));

            // Calcular o peso total (peso inicial + lastro)
            BigDecimal pesoTotal = peso.add(BigDecimal.valueOf(lastro));

            // Verificar se o peso total é maior que o peso esperado da corrida
            if (pesoTotal.compareTo(BigDecimal.valueOf(pesoParaCorrida)) < 0) {
                throw new CheckException("Peso insuficiente para a corrida. Peso esperado: " + pesoParaCorrida + ". Peso total: " + pesoTotal);
            }
        } else {
            throw new CheckException("Classificação da corrida não contém o formato esperado.");
        }
    }
}
