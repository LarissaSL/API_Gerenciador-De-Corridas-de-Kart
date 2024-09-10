package com.manascode.api_sgk.interfaceAdaptadores.api;

import com.manascode.api_sgk.aplicacao.corrida.AtualizarCorridaDTO;
import com.manascode.api_sgk.aplicacao.corrida.CorridaService;
import com.manascode.api_sgk.aplicacao.corrida.CriarCorridaDTO;
import com.manascode.api_sgk.aplicacao.corrida.ListarCorridaDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/corrida")
public class CorridaController {

    @Autowired
    private CorridaService service;

    @PostMapping
    @Transactional
    public ResponseEntity criarCorrida(@RequestBody @Valid CriarCorridaDTO dados) {
        return service.cadastrar(dados);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharCorrida(@PathVariable Long id) {
        return service.detalhar(id);
    }

    @GetMapping
    public ResponseEntity<Page<ListarCorridaDTO>> listarTodosComFiltros(
            @PageableDefault(size = 10, sort = {"data"}) Pageable paginacao,
            @RequestParam(required = false) String kartodromo,
            @RequestParam(required = false) String mes,
            @RequestParam(required = false) String dia,
            @RequestParam(required = false) String nome) {

        return service.listarTodosComFiltros(paginacao, kartodromo, mes, dia, nome);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarCorrida(@RequestBody @Valid AtualizarCorridaDTO dados) {
        return service.atualizar(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirCorrida(@PathVariable Long id) {
        return service.excluir(id);
    }
}
