package com.manascode.api_sgk.aplicacao.inscricao.validacoes;

import com.manascode.api_sgk.aplicacao.inscricao.AtualizarInscricaoDTO;
import com.manascode.api_sgk.aplicacao.inscricao.CriarInscricaoDTO;
import com.manascode.api_sgk.dominio.corrida.Corrida;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.InscricaoException;
import com.manascode.api_sgk.infraestrutura.persistencia.CorridaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorChaveEstrangeiraCorrida implements IValidadorInscricao {

    @Autowired
    private CorridaRepository repositorio;

    @Override
    public void validar(CriarInscricaoDTO dados) {
        verificar(dados.corridaId());
    }

    @Override
    public void validar(AtualizarInscricaoDTO dados) {
        if (dados.corridaId() != null) {
            verificar(dados.corridaId());
        }
    }

    public void verificar(Long idCorrida) {
        Corrida corrida = repositorio.getReferenceById(idCorrida);

        if (corrida == null) {
            throw new InscricaoException("Corrida não existe no sistema.");
        }

        if (!corrida.getAtivo()) {
            throw new InscricaoException("Corrida precisa estar ativa no sistema.");
        }
    }
}
