package com.manascode.api_sgk.interfaceAdaptadores.mapper;

import com.manascode.api_sgk.aplicacao.sorteador.DetalharInformacaoPilotoDoSorteioDTO;
import com.manascode.api_sgk.aplicacao.sorteador.ListarUsuariosParaSorteioDTO;
import com.manascode.api_sgk.aplicacao.sorteador.validacoes.DetalharInformacaoGeralDoSorteioDTO;
import com.manascode.api_sgk.aplicacao.usuario.DetalharNomeESobrenomeUsuarioProjecao;
import com.manascode.api_sgk.dominio.inscricao.Inscricao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SorteadorMapper {

    @Mapping(source = "inscricao.usuario.nome", target = "nome")
    @Mapping(source = "inscricao.usuario.sobrenome", target = "sobrenome")
    @Mapping(source = "numeroDoKart", target = "numeroDoKart")
    DetalharInformacaoPilotoDoSorteioDTO converteInscricaoENumeroDoKartEmDetalharInformacaoDoSorteioDto(Inscricao inscricao, Integer numeroDoKart);


    @Mapping(source = "listaDeInformacoesDoSorteio", target = "pilotos")
    DetalharInformacaoGeralDoSorteioDTO criarDetalharInformacaoGeralDoSorteioDtoComDetalharInformacaoDoPilotoSorteioNumeroDeKartListaSorteados(
            int totalDeNumerosSorteados,
            List<Integer> listaNumerosRestantes,
            List<DetalharInformacaoPilotoDoSorteioDTO> listaDeInformacoesDoSorteio,
            List<Integer> listaNumerosForaDoSorteio);


    ListarUsuariosParaSorteioDTO converteParaListarUsuariosParaSorteioDTO(DetalharNomeESobrenomeUsuarioProjecao usuario);
}
