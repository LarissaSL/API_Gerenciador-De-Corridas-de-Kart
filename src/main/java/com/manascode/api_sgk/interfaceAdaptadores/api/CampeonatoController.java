package com.manascode.api_sgk.interfaceAdaptadores.api;

import com.manascode.api_sgk.aplicacao.campeonato.CampeonatoService;
import com.manascode.api_sgk.aplicacao.campeonato.CriarCampeonatoDTO;
import com.manascode.api_sgk.aplicacao.campeonato.ListarCampeonatoDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/campeonato")
public class CampeonatoController {

    @Autowired
    private CampeonatoService service;

    @PostMapping
    @Transactional
    public ResponseEntity criarCampeonato(@RequestBody @Valid CriarCampeonatoDTO dados) {
        return service.cadastrar(dados);
    }

    @GetMapping
    public ResponseEntity<Page<ListarCampeonatoDTO>> listarTodos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return service.listarTodos(paginacao);
    }


    @GetMapping("/{id}")
    public ResponseEntity detalharUsuario(@PathVariable Long id) {
        return service.detalhar(id);
    }


}
