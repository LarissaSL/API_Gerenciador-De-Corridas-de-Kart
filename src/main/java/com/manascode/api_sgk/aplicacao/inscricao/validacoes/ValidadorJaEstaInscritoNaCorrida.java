package com.manascode.api_sgk.aplicacao.inscricao.validacoes;

import com.manascode.api_sgk.aplicacao.inscricao.AtualizarInscricaoDTO;
import com.manascode.api_sgk.aplicacao.inscricao.CriarInscricaoDTO;
import com.manascode.api_sgk.dominio.inscricao.Inscricao;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.InscricaoException;
import com.manascode.api_sgk.infraestrutura.persistencia.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorJaEstaInscritoNaCorrida implements IValidadorInscricao{
    @Autowired
    private InscricaoRepository repositorio;

    @Override
    public void validar(CriarInscricaoDTO dados) {
        verificar(dados.usuarioId(), dados.corridaId(), null);
    }

    @Override
    public void validar(AtualizarInscricaoDTO dados) {
        if (dados.corridaId() != null && dados.usuarioId() != null) {
            verificar(dados.usuarioId(), dados.corridaId(), dados.id());
        }

        Inscricao inscricao = repositorio.getReferenceById(dados.id());
        verificar(dados.usuarioId() != null ? dados.usuarioId() : inscricao.getUsuario().getId(),
                dados.corridaId() != null ? dados.corridaId() : inscricao.getCorrida().getId(),
                dados.id());

    }

    public void verificar(Long idUsuario, Long idCorrida, Long idInscricao) {
        Long qtdDeInscricoesDoUsuarioNaCorrida = repositorio.contarInscricoesPorUsuarioECorrida(idUsuario, idCorrida, idInscricao);

        if (qtdDeInscricoesDoUsuarioNaCorrida >= 1) {
            throw new InscricaoException("Esse usuário já está inscrito nessa corrida.");
        }
    }
}
