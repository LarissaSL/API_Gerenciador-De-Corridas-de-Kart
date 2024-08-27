package com.manascode.api_sgk.aplicacao.usuario.validacoes;

import com.manascode.api_sgk.aplicacao.usuario.CriarUsuarioDTO;
import com.manascode.api_sgk.infraestrutura.excecao.UsuarioException;
import com.manascode.api_sgk.infraestrutura.persistencia.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class ValidadorDeIdadeMinima implements IValidadorDeUsuario{

    @Override
    public void validar(CriarUsuarioDTO dados) {
        LocalDate dataDeNascimento = dados.data_de_nascimento();
        LocalDate dataAtual = LocalDate.now();

        int idadeDoUsuario = Period.between(dataDeNascimento, dataAtual).getYears();

        if (idadeDoUsuario < 15) {
            throw new UsuarioException("O usuário precisa ter a idade minima de 15 anos");
        }
    }
}
