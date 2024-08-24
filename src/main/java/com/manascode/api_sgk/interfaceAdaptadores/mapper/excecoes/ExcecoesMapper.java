package com.manascode.api_sgk.interfaceAdaptadores.mapper.excecoes;

import com.manascode.api_sgk.infraestrutura.excecao.ResponseErrorPadraoRFC;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;

@Mapper
public interface ExcecoesMapper {

    ExcecoesMapper INSTANCE = Mappers.getMapper(ExcecoesMapper.class);

    @Mapping(target = "type", constant = "http://ckc.com/problemas/erros-de-cadastro")
    @Mapping(target = "title", source = "titulo")
    @Mapping(target = "status", source = "httpStatus")
    @Mapping(target = "details", source = "exceptionMessage")
    @Mapping(target = "instance", source = "uri")
    @Mapping(target = "timestamp", expression = "java(java.time.LocalDateTime.now())")
    ResponseErrorPadraoRFC converteExcecaoParaDtoRFC(String titulo, String exceptionMessage, HttpStatus httpStatus, String uri);

}
