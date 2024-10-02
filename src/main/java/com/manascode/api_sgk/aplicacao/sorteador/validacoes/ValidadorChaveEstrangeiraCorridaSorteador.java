package com.manascode.api_sgk.aplicacao.sorteador.validacoes;

import com.manascode.api_sgk.dominio.corrida.Corrida;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.SorteioException;
import com.manascode.api_sgk.infraestrutura.persistencia.CorridaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorChaveEstrangeiraCorridaSorteador implements IValidadorSorteio {

    @Autowired
    private CorridaRepository repositorio;

    @Override
    public void validar(Long idCorrida) {
        Corrida corrida = repositorio.getReferenceById(idCorrida);

        if (corrida == null) {
            throw new SorteioException("Corrida não existe no sistema.");
        }
    }
}
