package com.manascode.api_sgk.aplicacao.campeonato;

import com.manascode.api_sgk.aplicacao.campeonato.validacoes.IValidadorDeCampeonatos;
import com.manascode.api_sgk.dominio.campeonato.Campeonato;
import com.manascode.api_sgk.infraestrutura.persistencia.CampeonatoRepository;
import com.manascode.api_sgk.interfaceAdaptadores.mapper.CampeonatoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@Service
public class CampeonatoService {

    @Autowired
    private CampeonatoRepository repositorio;

    @Autowired
    private CampeonatoMapper campeonatoMapper;

    @Autowired
    private DefinirSiglaService definirSiglaService;

    @Autowired
    private List<IValidadorDeCampeonatos> validadores;

    public ResponseEntity<DetalharCampeonatoDTO> cadastrar(CriarCampeonatoDTO dados) {
        validadores.forEach(v -> v.validar(dados));

        Campeonato campeonato = campeonatoMapper.converteCriarCampeonatoDTOEmCampeonato(dados);

        String sigla = definirSiglaService.definirSigla(dados.nome());
        campeonato.setSigla(sigla);

        Campeonato campeonatoSalvo = repositorio.save(campeonato);

        DetalharCampeonatoDTO campeonatoDetalhado = campeonatoMapper.converteCampeonatoEmDetalharCampeonatoDTO(campeonatoSalvo);

        var uri = UriComponentsBuilder
                .fromPath("/campeonato/{id}")
                .buildAndExpand(campeonatoSalvo.getId())
                .toUri();

        return ResponseEntity.created(uri).body(campeonatoDetalhado);
    }
}
