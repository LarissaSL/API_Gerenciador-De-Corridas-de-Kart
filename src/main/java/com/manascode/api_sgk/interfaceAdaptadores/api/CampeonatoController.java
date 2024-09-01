package com.manascode.api_sgk.interfaceAdaptadores.api;

import com.manascode.api_sgk.aplicacao.campeonato.CampeonatoService;
import com.manascode.api_sgk.aplicacao.campeonato.CriarCampeonatoDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
