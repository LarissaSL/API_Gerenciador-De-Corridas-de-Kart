package com.manascode.api_sgk.aplicacao.sorteador.validacoes;

import java.util.List;

public interface IValidadorNumerosDoSorteio {
    boolean validar(Integer numeroSorteado, List<Integer> listaNumerosForaDoSorteio, List<Integer> listaNumerosSorteados);
}
