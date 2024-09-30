package com.manascode.api_sgk.interfaceAdaptadores.mapper;

import com.manascode.api_sgk.aplicacao.corrida.CriarCorridaDTO;
import com.manascode.api_sgk.aplicacao.corrida.DetalharCorridaDTO;
import com.manascode.api_sgk.aplicacao.corrida.ListarCorridaDTO;
import com.manascode.api_sgk.dominio.campeonato.Campeonato;
import com.manascode.api_sgk.dominio.corrida.Corrida;
import com.manascode.api_sgk.dominio.kartodromo.Kartodromo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CorridaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ativo", ignore = true)
    @Mapping(source = "dadosCorrida.nome", target = "nome")
    @Mapping(source = "dadosCorrida.classificacao", target = "classificacao")
    Corrida converteCriarCorridaDtoEmCorrida(CriarCorridaDTO dadosCorrida, Kartodromo kartodromo, Campeonato campeonato);

    @Mapping(source = "kartodromo.id", target = "kartodromo.id")
    @Mapping(source = "kartodromo.nome", target = "kartodromo.nome")
    @Mapping(source = "campeonato.id", target = "campeonato.id")
    @Mapping(source = "campeonato.nome", target = "campeonato.nome")
    @Mapping(source = "dadosCorrida.classificacao", target = "classificacao")
    DetalharCorridaDTO converteCorridaEmDetalharCorridaDto(Corrida dadosCorrida);

    @Mapping(source = "kartodromo.nome", target = "kartodromoNome")
    @Mapping(source = "campeonato.nome", target = "campeonatoNome")
    @Mapping(source = "dadosCorrida.classificacao", target = "classificacao")
    ListarCorridaDTO converteCorridaEmListarCorridaDto(Corrida dadosCorrida);
}
