package com.manascode.api_sgk.dominio.campeonato;

import com.manascode.api_sgk.aplicacao.campeonato.AtualizarCampeonatoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "campeonatos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Campeonato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sigla;

    @Column(name = "data_inicial")
    private LocalDate dataInicial;

    @Column(name = "data_final")
    private LocalDate dataFinal;

    @Column(columnDefinition = "TINYINT(1)")
    private Boolean ativo = true;

    public void atualizar(AtualizarCampeonatoDTO dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.dataInicial() != null) {
            this.dataInicial = dados.dataInicial();
        }

        if (dados.dataFinal() != null) {
            this.dataFinal = dados.dataFinal();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
