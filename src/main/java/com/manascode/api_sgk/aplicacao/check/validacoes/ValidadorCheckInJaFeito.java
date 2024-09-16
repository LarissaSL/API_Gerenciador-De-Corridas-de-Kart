package com.manascode.api_sgk.aplicacao.check.validacoes;

import com.manascode.api_sgk.aplicacao.check.AtualizarCheckInDTO;
import com.manascode.api_sgk.aplicacao.check.CriarCheckInDTO;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CheckException;
import com.manascode.api_sgk.infraestrutura.persistencia.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCheckInJaFeito implements IValidadorCheckIn{

    @Autowired
    private CheckRepository repositorio;

    @Override
    public void validar(CriarCheckInDTO dados) {
        verificar(dados.idInscricao());
    }

    @Override
    public void validar(AtualizarCheckInDTO dados) {

    }

    public void verificar(Long idInscricao) {
        // Verificar se existe um registro de check-in com o idInscricao fornecido
        boolean checkInExists = repositorio.existsByInscricaoId(idInscricao);

        if (checkInExists) {
            throw  new CheckException("Já foi feito um check-in para essa inscrição.");
        }
    }
}
