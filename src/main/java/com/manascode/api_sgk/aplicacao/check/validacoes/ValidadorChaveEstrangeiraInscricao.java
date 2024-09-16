package com.manascode.api_sgk.aplicacao.check.validacoes;

import com.manascode.api_sgk.aplicacao.check.AtualizarCheckInDTO;
import com.manascode.api_sgk.aplicacao.check.CriarCheckInDTO;
import com.manascode.api_sgk.dominio.inscricao.Inscricao;
import com.manascode.api_sgk.dominio.inscricao.StatusPagamento;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CheckException;
import com.manascode.api_sgk.infraestrutura.persistencia.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorChaveEstrangeiraInscricao implements IValidadorCheckIn{

    @Autowired
    private InscricaoRepository repositorio;


    @Override
    public void validar(CriarCheckInDTO dados) {
        verificar(dados.idInscricao());
    }

    @Override
    public void validar(AtualizarCheckInDTO dados) {

    }

    public void verificar(Long idInscricao) {
        Inscricao inscricao = repositorio.getReferenceById(idInscricao);

        if (inscricao == null) {
            throw new CheckException("Inscrição não existe no sistema.");
        }

        if (!inscricao.getStatusPagamento().equals(StatusPagamento.pago)) {
            throw new CheckException("Não é permitido fazer check-ins sem pagar a Inscrição na Corrida.");
        }
    }
}
