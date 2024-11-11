package com.manascode.api_sgk.infraestrutura.excecao.aplicacao;

public class LoginTemporarioException extends RuntimeException {
    public LoginTemporarioException(String message) {
        super(message);
    }
}
