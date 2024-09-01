package com.manascode.api_sgk.aplicacao.campeonato.validacoes;

import com.manascode.api_sgk.aplicacao.campeonato.AtualizarCampeonatoDTO;
import com.manascode.api_sgk.aplicacao.campeonato.CriarCampeonatoDTO;
import com.manascode.api_sgk.aplicacao.campeonato.DefinirSiglaService;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CampeonatoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorNomeCampeonato implements IValidadorDeCampeonatos{
    @Autowired
    private DefinirSiglaService service;

    @Override
    public void validar(CriarCampeonatoDTO dados) {
        validarNome(dados.nome());
    }

    @Override
    public void validar(AtualizarCampeonatoDTO dados) {
        return;
    }

    public void validarNome (String nome) {
        String existeSiglaDeCampeonatoValido = service.definirSigla(nome);
        if (!existeSiglaDeCampeonatoValido.equals("CKC") && !existeSiglaDeCampeonatoValido.equals("DDL")) {
            throw new CampeonatoException(existeSiglaDeCampeonatoValido);
        }
    }
}
