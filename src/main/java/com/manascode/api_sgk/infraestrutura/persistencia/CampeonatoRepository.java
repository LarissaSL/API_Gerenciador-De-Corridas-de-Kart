package com.manascode.api_sgk.infraestrutura.persistencia;

import com.manascode.api_sgk.dominio.campeonato.Campeonato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface CampeonatoRepository extends JpaRepository<Campeonato, Long> {

    boolean existsByNomeAndDataInicialAndDataFinal(String nome, LocalDate dataInicial, LocalDate dataFinal);
}
