package com.manascode.api_sgk.interfaceAdaptadores.api;

import com.manascode.api_sgk.aplicacao.inscricao.AtualizarInscricaoDTO;
import com.manascode.api_sgk.aplicacao.inscricao.CriarInscricaoDTO;
import com.manascode.api_sgk.aplicacao.inscricao.InscricaoService;
import com.manascode.api_sgk.aplicacao.inscricao.ListarInscricaoDTO;
import com.manascode.api_sgk.dominio.inscricao.StatusPagamento;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inscricao")
public class InscricaoController {

    @Autowired
    private InscricaoService service;

    @PostMapping
    @Transactional
    public ResponseEntity fazerInscricao (@RequestBody @Valid CriarInscricaoDTO dados) {
        return service.fazerInscricao(dados);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharInscricao(@PathVariable Long id) {
        return service.detalhar(id);
    }

    @GetMapping
    public ResponseEntity<Page<ListarInscricaoDTO>> listarTodosComFiltros(
            @PageableDefault(size = 10, sort = {"corrida.id"}) Pageable paginacao,
            @RequestParam(required = false) Long id_corrida,
            @RequestParam(required = false) Boolean check,
            @RequestParam(required = false) StatusPagamento status_pagamento) {

        return service.listarTodos(paginacao, id_corrida, check, status_pagamento);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarInscricao(@RequestBody @Valid AtualizarInscricaoDTO dados) {
        return service.atualizar(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirInscricao(@PathVariable Long id) {
        return service.excluir(id);
    }
}
