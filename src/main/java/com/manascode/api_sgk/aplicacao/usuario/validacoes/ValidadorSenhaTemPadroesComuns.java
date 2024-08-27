package com.manascode.api_sgk.aplicacao.usuario.validacoes;

import com.manascode.api_sgk.aplicacao.usuario.CriarUsuarioDTO;
import com.manascode.api_sgk.infraestrutura.excecao.UsuarioException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ValidadorSenhaTemPadroesComuns implements IValidadorDeUsuario{
    @Override
    public void validar(CriarUsuarioDTO dados) {
        List<String> listaDePadroesComuns = Arrays.asList(
                "123", "abc", "password", "senha", "111", "000",
                "admin", "123456", dados.nome(), dados.sobrenome()
        );

        for (String padrao : listaDePadroesComuns) {
            if (dados.senha().toLowerCase().contains(padrao.toLowerCase())) {
                throw new UsuarioException("A senha não pode conter o padrão: '" + padrao + "'. Por favor, insira uma senha mais forte.");
            }
        }
    }
}
