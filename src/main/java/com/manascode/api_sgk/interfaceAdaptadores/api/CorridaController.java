package com.manascode.api_sgk.interfaceAdaptadores.api;

import com.manascode.api_sgk.aplicacao.corrida.CorridaService;
import jakarta.transaction.Transactional;
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
    public ResponseEntity criarCorrida() {
        return service.cadastrar();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharCorrida(@PathVariable Long id) {
        return service.detalhar();
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
