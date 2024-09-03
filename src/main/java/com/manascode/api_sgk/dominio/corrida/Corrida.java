package com.manascode.api_sgk.dominio.corrida;

import com.manascode.api_sgk.dominio.campeonato.Campeonato;
import com.manascode.api_sgk.dominio.kartodromo.Kartodromo;
import jakarta.persistence.*;
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
    @JoinColumn(name = "campeonato_id")
    private Campeonato campeonato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kartodromo_id")
    private Kartodromo kartodromo;

    private LocalDate data;
    private LocalTime horario;
    private Boolean transmissao = false;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @Column(unique = true)
    private String codigo;

    @Column(name = "preco", precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(name = "ativo", columnDefinition = "TINYINT(1)")
    private boolean ativo = true;
}
