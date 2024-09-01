package com.manascode.api_sgk.infraestrutura.persistencia;

import com.manascode.api_sgk.dominio.campeonato.Campeonato;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface CampeonatoRepository extends JpaRepository<Campeonato, Long> {

    boolean existsByNomeAndDataInicialAndDataFinal(String nome, LocalDate dataInicial, LocalDate dataFinal);
    Page<Campeonato> findAllByAtivoTrue(Pageable paginacao);
    Campeonato findByIdAndAtivo(@NotNull Long id, Boolean status);
}
