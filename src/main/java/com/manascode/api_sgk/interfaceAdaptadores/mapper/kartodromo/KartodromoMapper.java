package com.manascode.api_sgk.interfaceAdaptadores.mapper.kartodromo;

import com.manascode.api_sgk.aplicacao.kartodromo.CriarKartodromoDTO;
import com.manascode.api_sgk.aplicacao.kartodromo.DetalharKartodromoDTO;
import com.manascode.api_sgk.aplicacao.kartodromo.ListarKartodromoDTO;
import com.manascode.api_sgk.dominio.kartodromo.Kartodromo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface KartodromoMapper {

    // Permitir criar Instancias
    KartodromoMapper INSTANCE = Mappers.getMapper(KartodromoMapper.class);

    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "ativo" , ignore = true)
    Kartodromo converteCriarKartodromoDtoParaKartodromo (CriarKartodromoDTO dadosKartodromo);
    DetalharKartodromoDTO converteKartodromoParaDetalharKartodromoDto (Kartodromo dadosKartodromo);
    ListarKartodromoDTO converteKartodromoParaListarKartodromoDto(Kartodromo dadosKartodromo);
}
