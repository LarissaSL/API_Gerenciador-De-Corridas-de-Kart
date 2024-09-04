package com.manascode.api_sgk.aplicacao.usuario.validacoes;

import com.manascode.api_sgk.aplicacao.usuario.AtualizarUsuarioDTO;
import com.manascode.api_sgk.aplicacao.usuario.CriarUsuarioDTO;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.UsuarioException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class ValidadorDeIdadeMinima implements IValidadorDeUsuario{

    @Override
    public void validar(CriarUsuarioDTO dados) {
        verificar(dados.dataDeNascimento());
    }

    @Override
    public void validar(AtualizarUsuarioDTO dados) {
        if (dados.dataDeNascimento() != null) {
            verificar(dados.dataDeNascimento());
        }
    }

    public void verificar(LocalDate data_de_nascimento) {
        LocalDate dataAtual = LocalDate.now();

        int idadeDoUsuario = Period.between(data_de_nascimento, dataAtual).getYears();

        if (idadeDoUsuario < 15) {
            throw new UsuarioException("O usuário precisa ter a idade minima de 15 anos");
        }
    }
}
