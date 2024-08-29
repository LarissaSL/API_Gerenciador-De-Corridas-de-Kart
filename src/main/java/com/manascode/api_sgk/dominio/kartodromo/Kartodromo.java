package com.manascode.api_sgk.dominio.kartodromo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "kartodromos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Kartodromo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Embedded
    private Endereco endereco;

    private String endereco_foto;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean ativo = true;
}
