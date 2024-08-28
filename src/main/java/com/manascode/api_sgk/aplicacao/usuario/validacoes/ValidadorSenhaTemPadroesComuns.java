package com.manascode.api_sgk.aplicacao.usuario.validacoes;

import com.manascode.api_sgk.aplicacao.usuario.AtualizarUsuarioDTO;
import com.manascode.api_sgk.aplicacao.usuario.CriarUsuarioDTO;
import com.manascode.api_sgk.dominio.usuario.Usuario;
import com.manascode.api_sgk.infraestrutura.excecao.UsuarioException;
import com.manascode.api_sgk.infraestrutura.persistencia.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ValidadorSenhaTemPadroesComuns implements IValidadorDeUsuario {

    @Autowired
    private UsuarioRepository repositorio;

    @Override
    public void validar(CriarUsuarioDTO dados) {
        validadorDeSenha(dados.nome(), dados.sobrenome(), dados.senha(), null);
    }

    @Override
    public void validar(AtualizarUsuarioDTO dados) {
        if (dados.senha().isBlank()) {
            return;
        }

        validadorDeSenha(dados.nome(), dados.sobrenome(), dados.senha(), dados.id());
    }

    private void validadorDeSenha(String nome, String sobrenome, String senha, Long id) {
        if (nome.isBlank() || sobrenome.isBlank()) {
            Usuario usuario = repositorio.getReferenceById(id);

            // Verificando qual deles esta vazio e atribuindo o valor conforme necessário
            nome = nome.isBlank() ? usuario.getNome() : nome;
            sobrenome = sobrenome.isBlank() ? usuario.getSobrenome() : sobrenome;
        }

        List<String> listaDePadroesComuns = Arrays.asList(
                "123", "abc", "password", "senha", "111", "000",
                "admin", "123456", nome, sobrenome
        );

        for (String padrao : listaDePadroesComuns) {
            if (senha.toLowerCase().contains(padrao.toLowerCase())) {
                throw new UsuarioException("A senha não pode conter o padrão: '" + padrao + "'. Por favor, insira uma senha mais forte.");
            }
        }
    }
}
