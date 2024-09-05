package com.manascode.api_sgk.dominio.corrida;

import com.manascode.api_sgk.aplicacao.corrida.AtualizarCorridaDTO;
import com.manascode.api_sgk.dominio.campeonato.Campeonato;
import com.manascode.api_sgk.dominio.kartodromo.Kartodromo;
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
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "corridas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Corrida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campeonatoId")
    private Campeonato campeonato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kartodromoId")
    private Kartodromo kartodromo;

    private String nome;

    private LocalDate data;
    private LocalTime horario;
    private Boolean transmissao = false;

    @Enumerated(EnumType.STRING)
    private Classificacao classificacao;

    private String categoria;

    @Column(unique = true)
    private String codigo;

    @Column(name = "preco", precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(name = "ativo", columnDefinition = "TINYINT(1)")
    private Boolean ativo = true;

    public void excluir() {
        this.ativo = false;
    }

    public void atualizar(AtualizarCorridaDTO dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.data() != null) {
            this.data = dados.data();
        }

        if (dados.horario() != null) {
            this.horario = dados.horario();
        }

        if (dados.transmissao() != null) {
            this.transmissao = dados.transmissao();
        }

        if (dados.categoria() != null) {
            this.categoria = dados.categoria();
        }

        if (dados.classificacao() != null) {
            this.classificacao = dados.classificacao();
        }

        if (dados.codigo() != null) {
            this.codigo = dados.codigo();
        }

        if (dados.preco() != null) {
            this.preco = dados.preco();
        }
    }
}
