package com.manascode.api_sgk.interfaceAdaptadores.mapper;


import com.manascode.api_sgk.aplicacao.logintemporario.SolicitarLoginTemporarioDTO;
import com.manascode.api_sgk.dominio.logintemporario.LoginTemporario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoginTemporarioMapper {

    @Mapping(target = "cpf", ignore = true)
    @Mapping(source = "email", target = "email")
    LoginTemporario toEntity(SolicitarLoginTemporarioDTO dto);
}
