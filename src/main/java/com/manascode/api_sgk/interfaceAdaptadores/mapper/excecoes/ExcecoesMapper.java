package com.manascode.api_sgk.interfaceAdaptadores.mapper.excecoes;

import com.manascode.api_sgk.infraestrutura.excecao.ResponseErrorPadraoRFC;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.http.HttpStatus;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExcecoesMapper {

    @Mapping(target = "type", constant = "http://ckc.com/problemas/erros")
    @Mapping(target = "title", source = "titulo")
    @Mapping(target = "status", source = "httpStatus")
    @Mapping(target = "details", source = "exceptionMessage")
    @Mapping(target = "instance", source = "uri")
    @Mapping(target = "timestamp", expression = "java(java.time.LocalDateTime.now())")
    ResponseErrorPadraoRFC converteExcecaoParaDtoRFC(String titulo, String exceptionMessage, HttpStatus httpStatus, String uri);

}
