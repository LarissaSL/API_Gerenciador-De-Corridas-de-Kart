package com.manascode.api_sgk.aplicacao.inscricao.validacoes;

import com.manascode.api_sgk.aplicacao.inscricao.AtualizarInscricaoDTO;
import com.manascode.api_sgk.aplicacao.inscricao.CriarInscricaoDTO;
import com.manascode.api_sgk.dominio.usuario.Usuario;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.InscricaoException;
import com.manascode.api_sgk.infraestrutura.persistencia.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorChaveEstrangeiraUsuario implements IValidadorInscricao {

    @Autowired
    private UsuarioRepository repositorio;


    @Override
    public void validar(CriarInscricaoDTO dados) {
        verificar(dados.usuarioId());
    }

    @Override
    public void validar(AtualizarInscricaoDTO dados) {
        if (dados.usuarioId() != null) {
            verificar(dados.usuarioId());
        }
    }

    public void verificar(Long idUsuario) {
        Usuario usuario = repositorio.getReferenceById(idUsuario);

        if (usuario == null) {
            throw new InscricaoException("Usuário não existe no sistema.");
        }

        if (!usuario.isAtivo()) {
            throw new InscricaoException("Usuário precisa estar ativo no sistema.");
        }
    }
}
