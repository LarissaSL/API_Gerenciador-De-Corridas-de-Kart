package com.manascode.api_sgk.aplicacao.inscricao;

import com.manascode.api_sgk.dominio.inscricao.Inscricao;
import com.manascode.api_sgk.dominio.inscricao.StatusPagamento;
import com.manascode.api_sgk.infraestrutura.persistencia.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaginacaoInscricaoService {

    @Autowired
    private InscricaoRepository repositorio;

    public Page<Inscricao> definirPaginacao(Pageable paginacao, Long idCorrida, StatusPagamento statusPagamento, Boolean check) {
        Page<Inscricao> page;
        Pageable pageable;

        check = check == null ? false : check;

        // Filtrar por ID da corrida e traz todas as inscrições por ordem de Inscricao
        if (idCorrida != null && Boolean.FALSE.equals(check) && statusPagamento == null) {
            page = repositorio.contarInscricoesPorIdCorridaEOrdemPorDataInscricao(idCorrida, paginacao);
        }
        // Filtrar por ID da corrida e Status de Pagamento
        else if (idCorrida != null && statusPagamento != null && Boolean.FALSE.equals(check)) {
            page = repositorio.contarInscricoesPorIdCorridaEStatus(idCorrida, statusPagamento, paginacao);
        }
        // Filtrar Inscrições por Status de Pagamento
        else if (idCorrida == null && statusPagamento != null && Boolean.FALSE.equals(check)) {
            page = repositorio.contarInscricoesPorStatusPagamento(statusPagamento, paginacao);
        }
        // Filtrar por Id da Corrida e para paginação para Check-in e Check-out
        else if (idCorrida != null && Boolean.TRUE.equals(check) && statusPagamento == null) {
            page = repositorio.contarInscricoesPorIdCorridaEStatus(idCorrida, StatusPagamento.pago, paginacao);
        }
        // Se não tiver nenhum parametro traz todas
        else {
            page = repositorio.findAllByAtivoTrue(paginacao);
        }

        return page;
    }
}
