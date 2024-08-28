package com.manascode.api_sgk.aplicacao.usuario.validacoes;

import com.manascode.api_sgk.aplicacao.usuario.AtualizarUsuarioDTO;
import com.manascode.api_sgk.aplicacao.usuario.CriarUsuarioDTO;
import com.manascode.api_sgk.infraestrutura.excecao.UsuarioException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class ValidadorDeIdadeMinima implements IValidadorDeUsuario{

    @Override
    public void validar(CriarUsuarioDTO dados) {
        validarData(dados.data_de_nascimento());
    }

    @Override
    public void validar(AtualizarUsuarioDTO dados) {
        if (dados.data_de_nascimento() == null) {
            return;
        }
        validarData(dados.data_de_nascimento());
    }

    public void validarData(LocalDate data_de_nascimento) {
        LocalDate dataAtual = LocalDate.now();

        int idadeDoUsuario = Period.between(data_de_nascimento, dataAtual).getYears();

        if (idadeDoUsuario < 15) {
            throw new UsuarioException("O usuário precisa ter a idade minima de 15 anos");
        }
    }
}
