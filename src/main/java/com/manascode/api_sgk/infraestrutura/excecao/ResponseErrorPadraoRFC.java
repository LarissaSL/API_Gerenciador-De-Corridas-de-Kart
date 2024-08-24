package com.manascode.api_sgk.infraestrutura.excecao;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
public record ResponseErrorPadraoRFC(String type,
                                     String title,
                                     HttpStatus status,
                                     String details,
                                     String instance,
                                     LocalDateTime timestamp) {
}