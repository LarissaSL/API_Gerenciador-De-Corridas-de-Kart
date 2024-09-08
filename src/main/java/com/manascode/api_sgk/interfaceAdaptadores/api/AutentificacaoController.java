package com.manascode.api_sgk.interfaceAdaptadores.api;

import com.manascode.api_sgk.aplicacao.usuario.AutenticacaoService;
import com.manascode.api_sgk.aplicacao.usuario.LoginUsuarioDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutentificacaoController {

    @Autowired
    private AutenticacaoService service;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginUsuarioDTO dados) {
        return service.login(dados);
    }
}
