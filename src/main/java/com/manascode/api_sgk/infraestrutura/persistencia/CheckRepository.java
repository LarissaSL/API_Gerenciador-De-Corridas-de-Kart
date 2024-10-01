package com.manascode.api_sgk.infraestrutura.persistencia;

import com.manascode.api_sgk.aplicacao.checkIn.CompartilharCheckInProjecao;
import com.manascode.api_sgk.dominio.check.Check;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CheckRepository extends JpaRepository<Check, Long> {
    @Query("SELECT c FROM Check c WHERE c.inscricao.id = :inscricaoId")
    Check encontrarPorInscricao(Long inscricaoId);

    boolean existsByInscricaoId(Long idInscricao);

    @Query("SELECT ch FROM Check ch " +
            "JOIN ch.inscricao i " +
            "JOIN i.corrida c " +
            "WHERE (:idCorrida IS NULL OR c.id = :idCorrida)" +
            "ORDER BY i.dataInscricao")
    Page<Check> listarCheckPorFiltrosIdCorrida(Pageable paginacao, Long idCorrida);

    @Query("SELECT c FROM Check c WHERE c.id = :CheckInId")
    Check encontrarPorId(Long CheckInId);

    @Query("SELECT COUNT(c) FROM Check c WHERE c.inscricao.corrida.id = :idCorrida")
    int contarCheckInsPorIdCorrida(Long idCorrida);


    @Query("SELECT i.usuario.nome AS nome, i.usuario.sobrenome AS sobrenome, c.numeroDoKart AS numeroDoKart " +
            "FROM Check c " +
            "JOIN c.inscricao i " +
            "WHERE i.corrida.id = :idCorrida")
    List<CompartilharCheckInProjecao> listarCheckInPorIdCorrida(Long idCorrida);
}
