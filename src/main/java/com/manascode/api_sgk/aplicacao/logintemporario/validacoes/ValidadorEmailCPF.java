package com.manascode.api_sgk.aplicacao.logintemporario.validacoes;

import com.manascode.api_sgk.aplicacao.logintemporario.SolicitarLoginTemporarioDTO;
import com.manascode.api_sgk.dominio.usuario.Usuario;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.LoginTemporarioException;
import com.manascode.api_sgk.infraestrutura.persistencia.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorEmailCPF implements IValidadorLoginTemporario{

        @Autowired
        UsuarioRepository usuarioRepository;

        @Override
        public void validar(SolicitarLoginTemporarioDTO dto) {
            Usuario usuario = usuarioRepository.findByEmail(dto.email());
            if (!usuario.getCpf().equals(dto.cpf())) {
                throw new LoginTemporarioException("CPF não pertence ao usuário.");
            }

        }
}

