package com.manascode.api_sgk.aplicacao.campeonato;

import com.manascode.api_sgk.aplicacao.campeonato.validacoes.IValidadorDeCampeonatos;
import com.manascode.api_sgk.dominio.campeonato.Campeonato;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CampeonatoException;
import com.manascode.api_sgk.infraestrutura.persistencia.CampeonatoRepository;
import com.manascode.api_sgk.interfaceAdaptadores.mapper.CampeonatoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    public ResponseEntity <DetalharCampeonatoDTO> detalhar(Long id) {
        Campeonato campeonatoSalvo = repositorio.findByIdAndAtivo(id, true);
        if (campeonatoSalvo == null) {
            throw new CampeonatoException("Campeonato não encontrado ou não está ativo.");
        }

        DetalharCampeonatoDTO campeonatoDetalhado = campeonatoMapper.converteCampeonatoEmDetalharCampeonatoDTO(campeonatoSalvo);

        return ResponseEntity.ok(campeonatoDetalhado);
    }

    public ResponseEntity<Page<ListarCampeonatoDTO>> listarTodos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        Page<Campeonato> page = repositorio.findAllByAtivoTrue(paginacao);
        Page<ListarCampeonatoDTO> listaDeCampeonatoDTO = page.map(campeonatoMapper::converteCampeonatoParaListarCampeonatoDTO);

        return ResponseEntity.ok(listaDeCampeonatoDTO);
    }

    public ResponseEntity<DetalharCampeonatoDTO> atualizar(AtualizarCampeonatoDTO dados) {
        Campeonato campeonatoSalvo = repositorio.findByIdAndAtivo(dados.id(), true);
        if (campeonatoSalvo == null) {
            throw new CampeonatoException("Campeonato não encontrado ou não está ativo.");
        }

        validadores.forEach(v -> v.validar(dados));

        campeonatoSalvo.atualizar(dados);
        String sigla = definirSiglaService.definirSigla(campeonatoSalvo.getNome());
        campeonatoSalvo.setSigla(sigla);

        DetalharCampeonatoDTO campeonatoDetalhado = campeonatoMapper.converteCampeonatoEmDetalharCampeonatoDTO(campeonatoSalvo);

        return ResponseEntity.ok(campeonatoDetalhado);
    }

    public ResponseEntity<Void> excluir (Long id) {
        Campeonato campeonato = repositorio.findByIdAndAtivo(id, true);
        if (campeonato == null) {
            throw new CampeonatoException("Campeonato não encontrado ou não está ativo.");
        }

        campeonato.excluir();
        repositorio.save(campeonato);

        return ResponseEntity.noContent().build();
    }
}
