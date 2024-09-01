package com.manascode.api_sgk.interfaceAdaptadores.mapper;

import com.manascode.api_sgk.aplicacao.campeonato.CriarCampeonatoDTO;
import com.manascode.api_sgk.aplicacao.campeonato.DetalharCampeonatoDTO;
import com.manascode.api_sgk.aplicacao.campeonato.ListarCampeonatoDTO;
import com.manascode.api_sgk.dominio.campeonato.Campeonato;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CampeonatoMapper {

    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "ativo" , ignore = true)
    @Mapping(target = "sigla" , ignore = true)
    Campeonato converteCriarCampeonatoDTOEmCampeonato(CriarCampeonatoDTO dadosCampeonato);
    DetalharCampeonatoDTO converteCampeonatoEmDetalharCampeonatoDTO (Campeonato dadosCampeonato);
    ListarCampeonatoDTO converteCampeonatoParaListarCampeonatoDTO(Campeonato dadosCampeonato);
}
