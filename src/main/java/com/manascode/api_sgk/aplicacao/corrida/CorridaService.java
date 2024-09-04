package com.manascode.api_sgk.aplicacao.corrida;

import com.manascode.api_sgk.aplicacao.corrida.validacoes.IValidadorCorrida;
import com.manascode.api_sgk.aplicacao.usuario.ListarUsuarioDTO;
import com.manascode.api_sgk.dominio.campeonato.Campeonato;
import com.manascode.api_sgk.dominio.corrida.Corrida;
import com.manascode.api_sgk.dominio.kartodromo.Kartodromo;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CorridaException;
import com.manascode.api_sgk.infraestrutura.persistencia.CampeonatoRepository;
import com.manascode.api_sgk.infraestrutura.persistencia.CorridaRepository;
import com.manascode.api_sgk.infraestrutura.persistencia.KartodromoRepository;
import com.manascode.api_sgk.interfaceAdaptadores.mapper.CorridaMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.List;

@Service
public class CorridaService {

    @Autowired
    private CorridaRepository repositorio;

    @Autowired
    private CorridaMapper corridaMapper;

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    @Autowired
    private KartodromoRepository kartodromoRepository;

    @Autowired
    private List<IValidadorCorrida> validadores;

    public ResponseEntity<DetalharCorridaDTO> cadastrar(CriarCorridaDTO dados) {
        validadores.forEach(v -> v.validar(dados));

        Kartodromo kartodromo = kartodromoRepository.getReferenceById(dados.kartodromo_id());
        Campeonato campeonato = campeonatoRepository.getReferenceById(dados.campeonato_id());


        Corrida corrida = corridaMapper.converteCriarCorridaDtoEmCorrida(dados, kartodromo, campeonato);
        if (corrida.getCategoria() == null) {
            corrida.setCategoria("LIVRE");
        }

        Corrida corridaSalva = repositorio.save(corrida);
        DetalharCorridaDTO corridaDetalhada = corridaMapper.converteCorridaEmDetalharCorridaDto(corridaSalva);

        var uri = UriComponentsBuilder
                .fromPath("/corrida/{id}")
                .buildAndExpand(corridaSalva.getId())
                .toUri();

        return ResponseEntity.created(uri).body(corridaDetalhada);
    }

    public ResponseEntity atualizar() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity detalhar(Long id) {
        Corrida corridaSalva = repositorio.findByIdAndAtivo(id, true);
        if (corridaSalva == null) {
            throw new CorridaException("Corrida não encontrada ou não está ativa.");
        }

        DetalharCorridaDTO corridaDetalhada = corridaMapper.converteCorridaEmDetalharCorridaDto(corridaSalva);

        return ResponseEntity.ok(corridaDetalhada);
    }

    public ResponseEntity<Page<ListarCorridaDTO>> listarTodos(@PageableDefault(size = 10, sort = {"data"}) Pageable paginacao) {
        Page<Corrida> page = repositorio.findAllByAtivoTrue(paginacao);
        Page<ListarCorridaDTO> listaDeCorridasDTO = page.map(corridaMapper::converteCorridaEmListarCorridaDto);

        return ResponseEntity.ok(listaDeCorridasDTO);
    }

    public ResponseEntity excluir(Long id) {
        Corrida corrida = repositorio.getReferenceById(id);
        if (corrida == null) {
            throw new CorridaException("Corrida não encontrada ou não está ativa.");
        }

        corrida.excluir();

        return ResponseEntity.noContent().build();
    }
}
