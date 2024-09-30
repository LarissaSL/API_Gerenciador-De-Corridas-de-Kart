package com.manascode.api_sgk.aplicacao.corrida;

import com.manascode.api_sgk.aplicacao.corrida.validacoes.IValidadorCorrida;
import com.manascode.api_sgk.aplicacao.corrida.validacoes.ValidadorParametrosDeBusca;
import com.manascode.api_sgk.dominio.campeonato.Campeonato;
import com.manascode.api_sgk.dominio.corrida.Corrida;
import com.manascode.api_sgk.dominio.kartodromo.Kartodromo;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CampeonatoException;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CorridaException;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.KartodromoException;
import com.manascode.api_sgk.infraestrutura.persistencia.CampeonatoRepository;
import com.manascode.api_sgk.infraestrutura.persistencia.CorridaRepository;
import com.manascode.api_sgk.infraestrutura.persistencia.KartodromoRepository;
import com.manascode.api_sgk.interfaceAdaptadores.mapper.CorridaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

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

    @Autowired
    private ValidadorParametrosDeBusca validadorParametrosService;

    public ResponseEntity<DetalharCorridaDTO> cadastrar(CriarCorridaDTO dados) {
        validadores.forEach(v -> v.validar(dados));

        Kartodromo kartodromo = kartodromoRepository.getReferenceById(dados.kartodromoId());
        Campeonato campeonato = campeonatoRepository.getReferenceById(dados.campeonatoId());


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

    public ResponseEntity atualizar(AtualizarCorridaDTO dados) {
        // Recupera a corrida existente
        Corrida corridaSalva = repositorio.findByIdAndAtivo(dados.id(), true);
        if (corridaSalva == null) {
            throw new CorridaException("Corrida não encontrada ou não está ativa.");
        }

        // Valida os dados
        validadores.forEach(v -> v.validar(dados));

        // Atualiza as informações da corrida e já salva esses novos dados
        corridaSalva.atualizar(dados);

        if (dados.campeonatoId() != null) {
            Campeonato campeonato = campeonatoRepository.findById(dados.campeonatoId())
                    .orElseThrow(() -> new CampeonatoException("Campeonato não encontrado."));
            corridaSalva.setCampeonato(campeonato);
        }

        if (dados.kartodromoId() != null) {
            Kartodromo kartodromo = kartodromoRepository.findById(dados.kartodromoId())
                    .orElseThrow(() -> new KartodromoException("Kartódromo não encontrado."));
            corridaSalva.setKartodromo(kartodromo);
        }

        // Exibir as novas informações da corrida
        DetalharCorridaDTO corridaDetalhada = corridaMapper.converteCorridaEmDetalharCorridaDto(corridaSalva);

        return ResponseEntity.ok(corridaDetalhada);
    }

    public ResponseEntity<DetalharCorridaDTO> detalhar(Long id) {
        Corrida corridaSalva = repositorio.findByIdAndAtivo(id, true);
        if (corridaSalva == null) {
            throw new CorridaException("Corrida não encontrada ou não está ativa.");
        }

        DetalharCorridaDTO corridaDetalhada = corridaMapper.converteCorridaEmDetalharCorridaDto(corridaSalva);

        return ResponseEntity.ok(corridaDetalhada);
    }

    public ResponseEntity<Page<ListarCorridaDTO>> listarTodosComFiltros(Pageable paginacao,
                                                                        String kartodromo,
                                                                        String mes,
                                                                        String dia,
                                                                        String nome,
                                                                        Boolean check) {

        Integer mesInt = validadorParametrosService.validarMes(mes);
        Integer diaInt = validadorParametrosService.validarDia(dia);

        Page<Corrida> page = null;

        // Verificando se foi passado o parametro de Check-in, pois se existir o filtro é para o APP Mobile
        if (check != null && check) {
            page = repositorio.listarCorridasComCheckinEComFiltrosDeNomeKartodromoMesDia(paginacao, kartodromo, mesInt, diaInt, nome);
        } else {
            page = repositorio.listarCorridasPorFiltrosDeNomeKartodromoMesDia(paginacao, kartodromo, mesInt, diaInt, nome);
        }

        Page<ListarCorridaDTO> listaDeCorridasDTO = page.map(corridaMapper::converteCorridaEmListarCorridaDto);

        return ResponseEntity.ok(listaDeCorridasDTO);
    }

    public ResponseEntity<Void> excluir(Long id) {
        Corrida corrida = repositorio.getReferenceById(id);
        if (corrida == null) {
            throw new CorridaException("Corrida não encontrada ou não está ativa.");
        }

        corrida.excluir();

        return ResponseEntity.noContent().build();
    }
}
