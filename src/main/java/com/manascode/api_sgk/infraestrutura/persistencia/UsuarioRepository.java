package com.manascode.api_sgk.infraestrutura.persistencia;

import com.manascode.api_sgk.dominio.usuario.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Page<Usuario> findAllByAtivoTrue(Pageable paginacao);
    Usuario findByIdAndAtivo(@NotNull Long id, Boolean status);
    Usuario findByEmail(@Email @NotBlank String email);
}
