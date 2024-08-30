package com.manascode.api_sgk.aplicacao.usuario.validacoes;

import com.manascode.api_sgk.aplicacao.usuario.AtualizarUsuarioDTO;
import com.manascode.api_sgk.aplicacao.usuario.CriarUsuarioDTO;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.UsuarioException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCpf implements IValidadorDeUsuario {

    @Override
    public void validar(CriarUsuarioDTO dados) {
        validadorCpf(dados.cpf());
    }

    @Override
    public void validar(AtualizarUsuarioDTO dados) {
        if(dados.cpf() == null) {
            return;
        }

        validadorCpf(dados.cpf());
    }

    public void validadorCpf(String cpf) {
        // Verificando se nao é uma sequencia de numeros (11111111111)
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            throw new UsuarioException("O CPF informado é inválido.");
        }

        // Calcula o primeiro dígito verificador
        int soma = 0;
        int peso = 10;
        for (int i = 0; i < 9; i++) {
            int num = cpf.charAt(i) - '0';
            soma += num * peso--;
        }
        int resto = 11 - (soma % 11);
        char dig10 = (resto == 10 || resto == 11) ? '0' : (char) (resto + '0');

        // Calcula o segundo dígito verificador
        soma = 0;
        peso = 11;
        for (int i = 0; i < 10; i++) {
            int num = cpf.charAt(i) - '0';
            soma += num * peso--;
        }
        resto = 11 - (soma % 11);
        char dig11 = (resto == 10 || resto == 11) ? '0' : (char) (resto + '0');

        if (dig10 != cpf.charAt(9) || dig11 != cpf.charAt(10)) {
            throw new UsuarioException("O CPF informado é inválido.");
        }
    }
}
