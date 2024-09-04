package com.manascode.api_sgk.interfaceAdaptadores.api;

import com.manascode.api_sgk.aplicacao.corrida.CorridaService;
import com.manascode.api_sgk.aplicacao.corrida.CriarCorridaDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity listarTodos() {
        return service.listarTodos();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarCorrida() {
        return service.atualizar();
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity excluirCorrida() {
        return service.excluir();
    }
}
