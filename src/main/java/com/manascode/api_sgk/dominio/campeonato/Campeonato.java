package com.manascode.api_sgk.dominio.campeonato;

import jakarta.persistence.*;
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

    @Column(name = "data_inicial")
    private LocalDate dataInicial;

    @Column(name = "data_final")
    private LocalDate dataFinal;

    @Column(columnDefinition = "TINYINT(1)")
    private Boolean ativo;
}
