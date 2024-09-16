package com.manascode.api_sgk.interfaceAdaptadores.mapper;

import com.manascode.api_sgk.aplicacao.check.CriarCheckInDTO;
import com.manascode.api_sgk.aplicacao.check.DetalharCheckDTO;
import com.manascode.api_sgk.aplicacao.check.ListarCheckInDTO;
import com.manascode.api_sgk.dominio.check.Check;
import com.manascode.api_sgk.dominio.inscricao.Inscricao;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CheckMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCheckin", ignore = true)
    @Mapping(source = "inscricao", target = "inscricao")
    Check converteCriarCheckInDtoEmCheck(@Valid CriarCheckInDTO dados, Inscricao inscricao);

    @Mapping(source = "inscricao", target = "inscricao")
    @Mapping(source = "pesoInicial", target = "pesoInicial")
    @Mapping(source = "pesoFinal", target = "pesoFinal")
    @Mapping(source = "numeroDoKart", target = "numeroDoKart")
    @Mapping(source = "lastro", target = "lastro")
    @Mapping(source = "classificado", target = "classificado")
    @Mapping(source = "dataCheckin", target = "dataDoCheckIn")
    @Mapping(source = "inscricao.corrida.campeonato.nome", target = "inscricao.corrida.campeonato")
    @Mapping(source = "inscricao.corrida.campeonato.sigla", target = "inscricao.corrida.sigla")
    DetalharCheckDTO converterCheckInEmDetalharCheckInDto(Check check);

    @Mapping(source = "inscricao.corrida.campeonato.nome", target = "inscricao.corrida.campeonato")
    @Mapping(source = "inscricao.corrida.campeonato.sigla", target = "inscricao.corrida.sigla")
    @Mapping(source = "inscricao.usuario", target = "inscricao.usuario")
    @Mapping(source = "dataCheckin", target = "dataDoCheckIn")
    ListarCheckInDTO converterCheckEmListarCheckInDto(Check check);
}
