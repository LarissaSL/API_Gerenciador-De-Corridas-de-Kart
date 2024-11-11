package com.manascode.api_sgk.interfaceAdaptadores.mapper;


import com.manascode.api_sgk.aplicacao.logintemporario.SolicitarLoginTemporarioDTO;
import com.manascode.api_sgk.dominio.logintemporario.LoginTemporario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoginTemporarioMapper {

    @Mapping(source = "cpf", target = "usuario.cpf")
    @Mapping(source = "email", target = "usuario.email")
    LoginTemporario converteSolicitarLoginTemporarioDtoEmLoginTemporario(SolicitarLoginTemporarioDTO dto);
}
