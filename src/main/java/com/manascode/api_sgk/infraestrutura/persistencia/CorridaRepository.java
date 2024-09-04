package com.manascode.api_sgk.infraestrutura.persistencia;

import com.manascode.api_sgk.dominio.corrida.Classificacao;
import com.manascode.api_sgk.dominio.corrida.Corrida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface CorridaRepository extends JpaRepository<Corrida, Long> {
    Corrida findByIdAndAtivo(Long id, boolean status);
    boolean existsByNomeAndDataAndHorarioAndClassificacaoAndCampeonatoId(
            String nome,
            LocalDate data,
            LocalTime horario,
            Classificacao classificacao,
            Long campeonatoId
    );
    int countByDataAndHorario(LocalDate data, LocalTime horario);

    Page<Corrida> findAllByAtivoTrue(Pageable paginacao);
}
