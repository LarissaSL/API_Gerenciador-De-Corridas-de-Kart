package com.manascode.api_sgk.infraestrutura.persistencia;

import com.manascode.api_sgk.dominio.inscricao.Inscricao;
import com.manascode.api_sgk.dominio.inscricao.StatusPagamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {
    @Query("SELECT COUNT(i) FROM Inscricao i WHERE i.usuario.id = :usuarioId AND i.corrida.id = :corridaId AND i.id <> :inscricaoId")
    long contarInscricoesPorUsuarioECorrida(Long usuarioId, Long corridaId, Long inscricaoId);

    @Query("""
            SELECT COUNT(i) FROM Inscricao i
            JOIN i.corrida c
            WHERE i.usuario.id = :usuarioId
            AND c.data = (SELECT c2.data FROM Corrida c2 WHERE c2.id = :corridaId)
            AND c.horario = (SELECT c2.horario FROM Corrida c2 WHERE c2.id = :corridaId)
            AND i.id <> :inscricaoId
            """)
    long contarInscricoesConflitantesDeDataEHorario(Long usuarioId,
                                                    Long corridaId,
                                                    Long inscricaoId);

    Inscricao findByIdAndAtivo(Long id, boolean status);

    Page<Inscricao> findAllByAtivoTrue(Pageable paginacao);

    @Query("SELECT i FROM Inscricao i WHERE i.corrida.id = :idCorrida AND i.ativo = true ORDER BY i.dataInscricao")
    Page<Inscricao> contarInscricoesPorIdCorridaEOrdemPorDataInscricao(Long idCorrida, Pageable paginacao);

    // Usar para o APP Mobile, aba de Check-in e Check-out
    @Query("SELECT i FROM Inscricao i WHERE i.corrida.id = :idCorrida AND i.statusPagamento = :status AND i.ativo = true ORDER BY i.dataInscricao")
    Page<Inscricao> contarInscricoesPorIdCorridaEStatus(Long idCorrida, StatusPagamento status, Pageable paginacao);

    @Query("SELECT i FROM Inscricao i WHERE i.statusPagamento = :statusPagamento")
    Page<Inscricao> contarInscricoesPorStatusPagamento(StatusPagamento statusPagamento, Pageable paginacao);
}
