package com.manascode.api_sgk.interfaceAdaptadores.api;

import com.manascode.api_sgk.aplicacao.kartodromo.AtualizarKartodromoDTO;
import com.manascode.api_sgk.aplicacao.kartodromo.CriarKartodromoDTO;
import com.manascode.api_sgk.aplicacao.kartodromo.KartodromoService;
import com.manascode.api_sgk.aplicacao.kartodromo.ListarKartodromoDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kartodromo")
public class KartodromoController {

    @Autowired
    private KartodromoService service;

    @PostMapping
    @Transactional
    public ResponseEntity criarKartodromo(@RequestBody @Valid CriarKartodromoDTO dados) {
        return service.cadastrar(dados);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalherKartodromo(@PathVariable Long id) {
        return service.detalhar(id);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirKartodromo(@PathVariable Long id) {
        return service.excluir(id);
    }

    @GetMapping
    public ResponseEntity<Page<ListarKartodromoDTO>> listarTodos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return service.listarTodos(paginacao);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarKartodromo(@RequestBody @Valid AtualizarKartodromoDTO dados) {
        return service.atualizar(dados);
    }
}
