package com.manascode.api_sgk.aplicacao.kartodromo;

import com.manascode.api_sgk.dominio.kartodromo.Kartodromo;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.KartodromoException;
import com.manascode.api_sgk.infraestrutura.persistencia.KartodromoRepository;
import com.manascode.api_sgk.interfaceAdaptadores.mapper.KartodromoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class KartodromoService {

    @Autowired
    private KartodromoRepository repositorio;

    @Autowired
    private KartodromoMapper kartodromoMapper;

    public ResponseEntity<DetalharKartodromoDTO> cadastrar(CriarKartodromoDTO dados) {
        Kartodromo kartodromo = kartodromoMapper.converteCriarKartodromoDtoParaKartodromo(dados);
        Kartodromo kartodromoSalvo = repositorio.save(kartodromo);

        DetalharKartodromoDTO kartodromoDetalhado = kartodromoMapper.converteKartodromoParaDetalharKartodromoDto(kartodromoSalvo);

        var uri = UriComponentsBuilder
                .fromPath("/kartodromo/{id}")
                .buildAndExpand(kartodromoSalvo.getId())
                .toUri();

        return ResponseEntity.created(uri).body(kartodromoDetalhado);
    }

    public ResponseEntity<DetalharKartodromoDTO> detalhar(Long id) {
        Kartodromo kartodromoSalvo = repositorio.findByIdAndAtivo(id, true);
        if (kartodromoSalvo == null) {
            throw new KartodromoException("Kartodromo não encontrado ou não está ativo.");
        }

        DetalharKartodromoDTO kartodromoDetalhado = kartodromoMapper.converteKartodromoParaDetalharKartodromoDto(kartodromoSalvo);

        return ResponseEntity.ok(kartodromoDetalhado);
    }


    public ResponseEntity excluir(Long id) {
        Kartodromo kartodromoSalvo = repositorio.findByIdAndAtivo(id, true);
        if (kartodromoSalvo == null) {
            throw new KartodromoException("Kartodromo não encontrado ou não está ativo.");
        }
        kartodromoSalvo.excluir();

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Page<ListarKartodromoDTO>> listarTodos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        Page<Kartodromo> page = repositorio.findAllByAtivoTrue(paginacao);
        Page<ListarKartodromoDTO> listaDeKartodromosDto = page.map(kartodromoMapper::converteKartodromoParaListarKartodromoDto);

        return ResponseEntity.ok(listaDeKartodromosDto);
    }

    public ResponseEntity<DetalharKartodromoDTO> atualizar(AtualizarKartodromoDTO dados) {
        Kartodromo kartodromoSalvo = repositorio.findByIdAndAtivo(dados.id(), true);
        if (kartodromoSalvo == null) {
            throw new KartodromoException("Kartodromo não encontrado ou não está ativo.");
        }

        // Atualiza o endereço somente se o objeto endereco não for null
        if (dados.endereco() != null) {
            // Verifica e atualiza cada campo do endereço
            if (isBlank(dados.endereco().getRua())) {
                dados.endereco().setRua(kartodromoSalvo.getEndereco().getRua());
            }

            if (isBlank(dados.endereco().getNumero())) {
                dados.endereco().setNumero(kartodromoSalvo.getEndereco().getNumero());
            }

            if (isBlank(dados.endereco().getBairro())) {
                dados.endereco().setBairro(kartodromoSalvo.getEndereco().getBairro());
            }

            if (isBlank(dados.endereco().getCep())) {
                dados.endereco().setCep(kartodromoSalvo.getEndereco().getCep());
            }

            if (isBlank(dados.endereco().getCidade())) {
                dados.endereco().setCidade(kartodromoSalvo.getEndereco().getCidade());
            }

            if (isBlank(dados.endereco().getEstado())) {
                dados.endereco().setEstado(kartodromoSalvo.getEndereco().getEstado());
            }
        }

        kartodromoSalvo.atualizar(dados);

        DetalharKartodromoDTO kartodromoDetalhado = kartodromoMapper.converteKartodromoParaDetalharKartodromoDto(kartodromoSalvo);

        return ResponseEntity.ok(kartodromoDetalhado);
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
