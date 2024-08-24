package com.manascode.api_sgk.infraestrutura.excecao;

public class CampoValidacaoException extends RuntimeException{
    public CampoValidacaoException(String mensagem) {
        super(mensagem);
    }
}
