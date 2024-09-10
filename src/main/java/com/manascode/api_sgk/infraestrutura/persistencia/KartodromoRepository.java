package com.manascode.api_sgk.infraestrutura.persistencia;

import com.manascode.api_sgk.dominio.kartodromo.Kartodromo;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KartodromoRepository extends JpaRepository<Kartodromo, Long> {
    Page<Kartodromo> findAllByAtivoTrue(Pageable paginacao);
    Kartodromo findByIdAndAtivo(@NotNull Long id, Boolean status);

    @Query("SELECT DISTINCT k.nome FROM Kartodromo k")
    List<String> listarNomesDistintosDeKartodromos();
}
