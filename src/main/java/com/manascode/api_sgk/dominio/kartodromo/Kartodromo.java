package com.manascode.api_sgk.dominio.kartodromo;

import com.manascode.api_sgk.aplicacao.kartodromo.AtualizarKartodromoDTO;
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

    public void excluir() {
        this.ativo = false;
    }

    public void atualizar(AtualizarKartodromoDTO dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.endereco_foto() != null) {
            this.endereco_foto = dados.endereco_foto();
        }

        if (dados.endereco() != null) {
            this.endereco = dados.endereco();
        }
    }
}
