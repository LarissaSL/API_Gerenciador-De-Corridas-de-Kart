package com.manascode.api_sgk.aplicacao.sorteador.validacoes;

import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.SorteioException;
import com.manascode.api_sgk.infraestrutura.persistencia.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorExisteCheckInFeitoNaCorrida  implements IValidadorSorteio{
    @Autowired
    private CheckRepository repositorio;

    @Override
    public void validar(Long idCorrida) {
        int totalDeCheckInPorCorrida = repositorio.contarCheckInsPorIdCorrida(idCorrida);

        if (totalDeCheckInPorCorrida < 1) {
            throw new SorteioException("Não é possível realizar o sorteio para uma corrida sem check-in.");
        }
    }
}
