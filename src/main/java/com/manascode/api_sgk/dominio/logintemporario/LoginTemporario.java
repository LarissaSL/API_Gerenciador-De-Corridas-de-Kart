package com.manascode.api_sgk.dominio.logintemporario;

import com.manascode.api_sgk.dominio.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "logins_temporarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class LoginTemporario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuariosId", nullable = false)
    private Usuario usuario;

    @Column(name = "codigo_temporario", length = 10)
    private String codigoTemporario;

    @Column(name = "data_expiracao")
    private LocalDateTime dataExpiracao;

    @Column(name = "utilizado", columnDefinition = "TINYINT(1)")
    private Boolean utilizado = false;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_utilizado")
    private LocalDateTime dataUtilizado;

    @PrePersist
    public void prePersist() {
        dataCriacao = LocalDateTime.now();
        dataExpiracao = LocalDateTime.now().plusMinutes(5);
    }

}
