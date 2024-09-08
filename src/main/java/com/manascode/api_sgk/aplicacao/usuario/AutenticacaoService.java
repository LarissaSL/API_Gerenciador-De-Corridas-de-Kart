package com.manascode.api_sgk.aplicacao.usuario;

import com.manascode.api_sgk.dominio.usuario.TipoUsuario;
import com.manascode.api_sgk.dominio.usuario.Usuario;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.AutenticacaoException;
import com.manascode.api_sgk.infraestrutura.persistencia.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    @Autowired
    private UsuarioRepository repositorio;

    public ResponseEntity login(@Valid LoginUsuarioDTO dados) {
        Usuario usuario = repositorio.findByEmail(dados.email());

        // Verificando se não existe o usuário e caso exista se ele não está ativo
        if (usuario == null || !usuario.isAtivo()) {
            throw new AutenticacaoException("Usuário não encontrado ou não está ativo.", HttpStatus.UNAUTHORIZED);
        }

        // Verificando se o Usuário é um Administrador/Organizador
        if (!usuario.getTipo().equals(TipoUsuario.admin)) {
            throw new AutenticacaoException("Apenas Organizadores podem usar esse aplicativo.", HttpStatus.FORBIDDEN);
        }

        // Verificando a senha do Usuário
        if (!usuario.getSenha().equalsIgnoreCase(dados.senha())) {
            throw new AutenticacaoException("Senha incorreta.", HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok().build();
    }
}
