package com.manascode.api_sgk.dominio.corrida;

public enum Categoria {
    NOVENTA_E_CINCO("95"),
    CENTO_E_DEZ("110"),
    LIVRE("Livre");

    private final String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Categoria fromDescricao(String descricao) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.getDescricao().equals(descricao)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Descrição inválida: " + descricao);
    }
}


