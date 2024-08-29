package com.manascode.api_sgk.aplicacao.kartodromo;

import com.manascode.api_sgk.dominio.kartodromo.Kartodromo;
import com.manascode.api_sgk.infraestrutura.excecao.KartodromoException;
import com.manascode.api_sgk.infraestrutura.persistencia.KartodromoRepository;
import com.manascode.api_sgk.interfaceAdaptadores.mapper.kartodromo.KartodromoMapper;
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

    public ResponseEntity<DetalharKartodromoDTO> cadastrar(CriarKartodromoDTO dados) {
        Kartodromo kartodromo = KartodromoMapper.INSTANCE.converteCriarKartodromoDtoParaKartodromo(dados);
        Kartodromo kartodromoSalvo = repositorio.save(kartodromo);

        DetalharKartodromoDTO kartodromoDetalhado = KartodromoMapper.INSTANCE.converteKartodromoParaDetalharKartodromoDto(kartodromoSalvo);

        var uri = UriComponentsBuilder
                .fromPath("/kartodromo/{id}")
                .buildAndExpand(kartodromoSalvo.getId())
                .toUri();

        return ResponseEntity.created(uri).body(kartodromoDetalhado);
    }

    public ResponseEntity<DetalharKartodromoDTO> detalhar(Long id) {
        Kartodromo kartodromoSalvo = repositorio.findById(id).orElseThrow(() -> new KartodromoException("Kartodromo não encontrado."));
        DetalharKartodromoDTO kartodromoDetalhado = KartodromoMapper.INSTANCE.converteKartodromoParaDetalharKartodromoDto(kartodromoSalvo);

        return ResponseEntity.ok(kartodromoDetalhado);
    }


    public ResponseEntity excluir(Long id) {
        Kartodromo kartodromoSalvo = repositorio.findById(id).orElseThrow(() -> new KartodromoException("Kartodromo não encontrado."));
        kartodromoSalvo.excluir();

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Page<ListarKartodromoDTO>> listarTodos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        Page<Kartodromo> page = repositorio.findAllByAtivoTrue(paginacao);
        Page<ListarKartodromoDTO> listaDeKartodromosDto = page.map(KartodromoMapper.INSTANCE::converteKartodromoParaListarKartodromoDto);

        return ResponseEntity.ok(listaDeKartodromosDto);
    }

    public ResponseEntity<DetalharKartodromoDTO> atualizar(AtualizarKartodromoDTO dados) {
        Kartodromo kartodromoSalvo = repositorio.findById(dados.id()).orElseThrow(() -> new KartodromoException("Kartodromo não encontrado."));

        // Atualiza o endereço somente se o objeto endereco não for null
        if (dados.endereco() != null) {
            // Verifica e atualiza cada campo do endereço
            if (isBlank(dados.endereco().getRua())) {
                dados.endereco().setRua(kartodromoSalvo.getEndereco().getRua());
                System.out.println(dados.endereco().getRua());
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

        DetalharKartodromoDTO kartodromoDetalhado = KartodromoMapper.INSTANCE.converteKartodromoParaDetalharKartodromoDto(kartodromoSalvo);

        return ResponseEntity.ok(kartodromoDetalhado);
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
