package com.manascode.api_sgk.aplicacao.sorteador.validacoes;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidadorNumeroJaSorteado implements IValidadorNumerosDoSorteio {

    @Override
    public boolean validar(Integer numeroSorteado, List<Integer> listaNumerosForaDoSorteio, List<Integer> listaNumerosSorteados) {
        if (listaNumerosSorteados.contains(numeroSorteado)) {
            return true;
        }
        return false;
    }
}
