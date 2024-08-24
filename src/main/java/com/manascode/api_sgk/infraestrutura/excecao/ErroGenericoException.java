package com.manascode.api_sgk.infraestrutura.excecao;

public class ErroGenericoException extends RuntimeException {
    public ErroGenericoException(String message) {
        super(message);
    }
}
