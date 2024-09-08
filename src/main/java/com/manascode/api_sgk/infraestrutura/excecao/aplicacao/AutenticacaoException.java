package com.manascode.api_sgk.infraestrutura.excecao.aplicacao;

import org.springframework.http.HttpStatus;

public class AutenticacaoException extends RuntimeException {
  private final HttpStatus status;

  public AutenticacaoException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }

  public AutenticacaoException(String message) {
    this(message, HttpStatus.UNAUTHORIZED);
  }

  public HttpStatus getStatus() {
    return status;
  }
}

