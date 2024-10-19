package com.manascode.api_sgk.aplicacao.checkOut.validacoes;

import com.manascode.api_sgk.aplicacao.checkOut.RealizarCheckOutDTO;
import com.manascode.api_sgk.dominio.check.Check;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CheckException;
import com.manascode.api_sgk.infraestrutura.persistencia.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCheckInExiste implements IValidadorCheckOut{

    @Autowired
    private CheckRepository repositorio;

    @Override
    public void validar(RealizarCheckOutDTO dados) {
        Check checkInSalvo = repositorio.encontrarPorIdInscricao(dados.idInscricao());

        if (checkInSalvo == null) {
            throw new CheckException("Check-in não encontrado.");
        }
    }
}
