package com.manascode.api_sgk.infraestrutura.persistencia;

import com.manascode.api_sgk.dominio.corrida.Corrida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorridaRepository extends JpaRepository<Corrida, Long> {
}
