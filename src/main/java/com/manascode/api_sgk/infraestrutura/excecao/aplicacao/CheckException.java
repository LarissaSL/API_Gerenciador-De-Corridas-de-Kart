package com.manascode.api_sgk.infraestrutura.excecao.aplicacao;

public class CheckException extends RuntimeException {
    public CheckException(String message) {
        super(message);
    }
}
