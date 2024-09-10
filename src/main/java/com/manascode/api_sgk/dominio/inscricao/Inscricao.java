package com.manascode.api_sgk.dominio.inscricao;

import com.manascode.api_sgk.aplicacao.inscricao.AtualizarInscricaoDTO;
import com.manascode.api_sgk.dominio.corrida.Corrida;
import com.manascode.api_sgk.dominio.usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "inscricoes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Inscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corrida_id")
    private Corrida corrida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pgto")
    private StatusPagamento statusPagamento;

    @Column(name = "data_inscricao")
    private LocalDateTime dataInscricao;

    @Column(name = "ativo", columnDefinition = "TINYINT(1)")
    private Boolean ativo = true;

    public void excluir() {
        this.statusPagamento = StatusPagamento.cancelado;
    }

    public void atualizar(@Valid AtualizarInscricaoDTO dados) {
        if (dados.statusPagamento() != null) {
            this.statusPagamento = dados.statusPagamento();
        }
    }
}
