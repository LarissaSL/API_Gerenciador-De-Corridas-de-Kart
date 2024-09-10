package com.manascode.api_sgk.interfaceAdaptadores.api;

import com.manascode.api_sgk.aplicacao.inscricao.AtualizarInscricaoDTO;
import com.manascode.api_sgk.aplicacao.inscricao.CriarInscricaoDTO;
import com.manascode.api_sgk.aplicacao.inscricao.InscricaoService;
import com.manascode.api_sgk.aplicacao.inscricao.ListarInscricaoDTO;
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
    public ResponseEntity<Page<ListarInscricaoDTO>> listarTodos(@PageableDefault(size = 10, sort = {"corrida.id"})Pageable paginacao) {
        return service.listarTodos(paginacao);
    }

    @GetMapping("/por-corrida")
    public ResponseEntity<Page<ListarInscricaoDTO>> listarTodosPorCorridaID(
            @RequestParam(name = "corrida-id") Long idCorrida,
            @PageableDefault(size = 10, sort = {"dataInscricao"}) Pageable paginacao) {

        return service.listarTodosPorCorridaID(idCorrida, paginacao);
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
