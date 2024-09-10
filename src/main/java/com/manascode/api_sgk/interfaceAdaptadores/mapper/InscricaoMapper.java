package com.manascode.api_sgk.interfaceAdaptadores.mapper;

import com.manascode.api_sgk.aplicacao.inscricao.CriarInscricaoDTO;
import com.manascode.api_sgk.aplicacao.inscricao.DetalharInscricaoDTO;
import com.manascode.api_sgk.aplicacao.inscricao.ListarInscricaoDTO;
import com.manascode.api_sgk.dominio.corrida.Corrida;
import com.manascode.api_sgk.dominio.inscricao.Inscricao;
import com.manascode.api_sgk.dominio.usuario.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InscricaoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ativo", ignore = true)
    @Mapping(source = "dadosInscricao.statusPagamento", target = "statusPagamento")
    @Mapping(source = "corrida", target = "corrida")
    @Mapping(source = "usuario", target = "usuario")
    Inscricao converteCriarInscricaoDtoEmInscricao(CriarInscricaoDTO dadosInscricao, Corrida corrida, Usuario usuario);

    @Mapping(source = "usuario.id", target = "usuario.id")
    @Mapping(source = "usuario.nome", target = "usuario.nome")
    @Mapping(source = "usuario.sobrenome", target = "usuario.sobrenome")
    @Mapping(source = "corrida.id", target = "corrida.id")
    @Mapping(source = "corrida.nome", target = "corrida.nome")
    @Mapping(source = "corrida.campeonato.nome", target = "corrida.campeonato")
    @Mapping(source = "corrida.campeonato.sigla", target = "corrida.sigla")
    @Mapping(source = "corrida.classificacao", target = "corrida.classificacao")
    @Mapping(source = "corrida.horario", target = "corrida.horario")
    @Mapping(source = "corrida.data", target = "corrida.data")
    DetalharInscricaoDTO converteInscricaoEmDetalharInscricaoDto(Inscricao dadosInscricao);

    @Mapping(source = "usuario.id", target = "usuario.id")
    @Mapping(source = "usuario.nome", target = "usuario.nome")
    @Mapping(source = "usuario.sobrenome", target = "usuario.sobrenome")
    @Mapping(source = "corrida.id", target = "corrida.id")
    @Mapping(source = "corrida.nome", target = "corrida.nome")
    @Mapping(source = "corrida.campeonato.nome", target = "corrida.campeonato")
    @Mapping(source = "corrida.campeonato.sigla", target = "corrida.sigla")
    @Mapping(source = "corrida.classificacao", target = "corrida.classificacao")
    @Mapping(source = "corrida.horario", target = "corrida.horario")
    @Mapping(source = "corrida.data", target = "corrida.data")
    ListarInscricaoDTO converteInscricaoEmListarCorridaDto(Inscricao inscricao);
}
