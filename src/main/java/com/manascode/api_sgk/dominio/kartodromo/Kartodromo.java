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

    @Column(name = "endereco_foto")
    private String enderecoFoto;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean ativo = true;

    public void excluir() {
        this.ativo = false;
    }

    public void atualizar(AtualizarKartodromoDTO dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.enderecoFoto() != null) {
            this.enderecoFoto = dados.enderecoFoto();
        }

        if (dados.endereco() != null) {
            this.endereco = dados.endereco();
        }
    }
}
