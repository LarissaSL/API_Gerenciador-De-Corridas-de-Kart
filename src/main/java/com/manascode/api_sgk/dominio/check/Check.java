package com.manascode.api_sgk.dominio.check;


import com.manascode.api_sgk.aplicacao.checkIn.AtualizarCheckInDTO;
import com.manascode.api_sgk.aplicacao.checkOut.RealizarCheckOutDTO;
import com.manascode.api_sgk.dominio.inscricao.Inscricao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "checkins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Check {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inscricao_id")
    private Inscricao inscricao;

    @Column(name = "peso_inicial", precision = 5, scale = 2)
    private BigDecimal pesoInicial;

    @Column(name = "peso_final", precision = 5, scale = 2)
    private BigDecimal pesoFinal;

    @Column(name = "numero_do_kart")
    private Integer numeroDoKart;

    private Integer lastro;

    @Column(name = "classificado", columnDefinition = "TINYINT(1)")
    private Boolean classificado = true;

    @Column(name = "data_checkin")
    private LocalDateTime dataCheckin;

    public void atualizarCheckIn(AtualizarCheckInDTO dados) {
        if (dados.pesoInicial() != null) {
            this.pesoInicial = dados.pesoInicial();
        }

        if (dados.lastro() != null) {
            this.lastro = dados.lastro();
        }
    }

    public void atualizarDadosParaCheckOut(RealizarCheckOutDTO dados) {
        if (dados.pesoFinal() != null) {
            this.pesoFinal = dados.pesoFinal();
        }

        if (dados.classificado() != null) {
            this.classificado = dados.classificado();
        }
    }

    public void inserirNumeroDeKart(int numeroDoKart) {
        this.numeroDoKart = numeroDoKart;
    }
}