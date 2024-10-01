package com.manascode.api_sgk.aplicacao.checkIn.validacoes;

import com.manascode.api_sgk.aplicacao.checkIn.AtualizarCheckInDTO;
import com.manascode.api_sgk.aplicacao.checkIn.CriarCheckInDTO;
import com.manascode.api_sgk.dominio.inscricao.Inscricao;
import com.manascode.api_sgk.dominio.usuario.Usuario;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CheckException;
import com.manascode.api_sgk.infraestrutura.persistencia.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorVerificarUsuarioAtivo implements IValidadorCheckIn{
    @Autowired
    private InscricaoRepository repositorio;

    @Override
    public void validar(CriarCheckInDTO dados) {
        verificar(dados.idInscricao());
    }

    @Override
    public void validar(AtualizarCheckInDTO dados) {
        verificar(dados.idInscricao());
    }

    public void verificar(Long idInscricao) {
        // Recupera a inscrição pelo ID
        Inscricao inscricao = repositorio.findById(idInscricao)
                .orElseThrow(() -> new CheckException("Inscrição não encontrada."));

        // Recupera o usuário associado à inscrição
        Usuario usuario = inscricao.getUsuario();
        if (usuario == null) {
            throw new CheckException("Usuário não encontrado.");
        }

        if (!usuario.isAtivo()) {
            throw new CheckException("Usuário não está ativo.");
        }

    }
}
