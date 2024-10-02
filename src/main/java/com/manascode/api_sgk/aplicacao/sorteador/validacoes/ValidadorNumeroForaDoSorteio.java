package com.manascode.api_sgk.aplicacao.sorteador.validacoes;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidadorNumeroForaDoSorteio implements IValidadorNumerosDoSorteio {

    @Override
    public boolean validar(Integer numeroSorteado, List<Integer> listaNumerosForaDoSorteio, List<Integer> listaNumerosSorteados) {
        if (listaNumerosForaDoSorteio.contains(numeroSorteado)) {
            return true;
        }
        return false;
    }
}
