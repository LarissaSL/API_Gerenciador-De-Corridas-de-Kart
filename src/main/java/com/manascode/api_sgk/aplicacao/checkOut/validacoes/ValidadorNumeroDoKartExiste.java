package com.manascode.api_sgk.aplicacao.checkOut.validacoes;

import com.manascode.api_sgk.aplicacao.checkOut.RealizarCheckOutDTO;
import com.manascode.api_sgk.dominio.check.Check;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CheckException;
import com.manascode.api_sgk.infraestrutura.persistencia.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorNumeroDoKartExiste implements IValidadorCheckOut{
    @Autowired
    private CheckRepository repositorio;

    @Override
    public void validar(RealizarCheckOutDTO dados) {
        Check checkInSalvo = repositorio.encontrarPorId(dados.idCheckIn());

        if (checkInSalvo.getNumeroDoKart() == null) {
            throw new CheckException("O Piloto não tem número de Kart, verifique se ele realmente correu e preencha o número do Kart.");
        }
    }
}
