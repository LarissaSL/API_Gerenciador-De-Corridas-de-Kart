package com.manascode.api_sgk.infraestrutura.persistencia;
import java.time.LocalDateTime;
import java.util.List;

import com.manascode.api_sgk.dominio.logintemporario.LoginTemporario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginTemporarioRepository extends JpaRepository<LoginTemporario, Long> {

    // Consulta logins temporários não utilizados e que ainda estão válidos para o usuário especificado
    List<LoginTemporario> findByUsuario_IdAndUtilizadoFalseAndDataExpiracaoAfter(Long usuarioId, LocalDateTime now);

    // Verifica se um código temporário específico já existe
    boolean existsByCodigoTemporario(String codigoTemporario);
}
