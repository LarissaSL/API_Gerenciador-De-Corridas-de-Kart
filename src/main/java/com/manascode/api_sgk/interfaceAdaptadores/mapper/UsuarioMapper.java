package com.manascode.api_sgk.interfaceAdaptadores.mapper;

import com.manascode.api_sgk.aplicacao.inscricao.DetalharInscricaoUsuarioDTO;
import com.manascode.api_sgk.aplicacao.usuario.CriarUsuarioDTO;
import com.manascode.api_sgk.aplicacao.usuario.DetalharUsuarioDTO;
import com.manascode.api_sgk.aplicacao.usuario.ListarUsuarioDTO;
import com.manascode.api_sgk.dominio.usuario.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {

    // Aqui ele está ignorando esses atributos, pois no DTO eles não existem
    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "ativo" , ignore = true)
    Usuario converteDTOParaUsuario(CriarUsuarioDTO dadosUsuario);
    DetalharUsuarioDTO converteUsuarioParaDTODetalhamento(Usuario dadosUsuario);
    ListarUsuarioDTO converteUsuarioParaListarUsuarioDTO(Usuario dadosUsuario);

    DetalharInscricaoUsuarioDTO converterUsuarioEmDetalharInscricaoUsuarioDto(Usuario usuario);
}
