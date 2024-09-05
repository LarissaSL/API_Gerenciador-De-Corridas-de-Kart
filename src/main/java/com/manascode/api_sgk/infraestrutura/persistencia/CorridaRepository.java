package com.manascode.api_sgk.infraestrutura.persistencia;

import com.manascode.api_sgk.dominio.corrida.Classificacao;
import com.manascode.api_sgk.dominio.corrida.Corrida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public interface CorridaRepository extends JpaRepository<Corrida, Long> {
    Corrida findByIdAndAtivo(Long id, boolean status);

    @Query("SELECT COUNT(c) > 0 FROM Corrida c WHERE c.nome = :nome AND c.data = :data AND c.horario = :horario AND c.classificacao = :classificacao AND c.campeonato.id = :campeonatoId AND c.id <> :id")
    boolean existsByNomeAndDataAndHorarioAndClassificacaoAndCampeonatoIdAndNotId(
            String nome,
            LocalDate data,
            LocalTime horario,
            Classificacao classificacao,
            Long campeonatoId,
            Long id
    );

    int countByDataAndHorario(LocalDate data, LocalTime horario);

    Page<Corrida> findAllByAtivoTrue(Pageable paginacao);
}
