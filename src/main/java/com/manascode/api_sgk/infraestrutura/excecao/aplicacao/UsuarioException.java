package com.manascode.api_sgk.infraestrutura.excecao.aplicacao;

public class UsuarioException extends RuntimeException {
    public UsuarioException(String message) {
        super(message);
    }
}
