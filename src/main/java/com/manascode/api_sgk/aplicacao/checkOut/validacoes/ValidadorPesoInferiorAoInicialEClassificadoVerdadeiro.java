package com.manascode.api_sgk.aplicacao.checkOut.validacoes;

import com.manascode.api_sgk.aplicacao.checkOut.RealizarCheckOutDTO;
import com.manascode.api_sgk.dominio.check.Check;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CheckException;
import com.manascode.api_sgk.infraestrutura.persistencia.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPesoInferiorAoInicialEClassificadoVerdadeiro implements IValidadorCheckOut{

    @Autowired
    private CheckRepository repositorio;

    @Override
    public void validar(RealizarCheckOutDTO dados) {
        // Faz essa validação apenas se o Peso Final for informado
        if (dados.pesoFinal() != null) {
            // Recuperando o Check-in do Banco, não está verificando se é nulo pois tem uma validação antes dessa que faz isso
            Check checkInSalvo = repositorio.encontrarPorIdInscricao(dados.idInscricao());

            // Definindo se o valor de classificado será o que está no banco ou o novo informado
            Boolean classificado = dados.classificado() == null ? checkInSalvo.getClassificado() : dados.classificado();

            if (dados.pesoFinal().compareTo(checkInSalvo.getPesoInicial()) < 0 && classificado) {
                throw new CheckException("Peso final é menor que inicial, isso indica uma possível desclassificação.");
            }
        }
    }
}
