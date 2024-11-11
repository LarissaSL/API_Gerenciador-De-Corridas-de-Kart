package com.manascode.api_sgk.interfaceAdaptadores.api;


import com.manascode.api_sgk.aplicacao.logintemporario.SolicitarLoginTemporarioDTO;
import com.manascode.api_sgk.aplicacao.logintemporario.LoginTemporarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logins-temporarios")
public class LoginTemporarioController {

    @Autowired
    private LoginTemporarioService loginTemporarioService;

    @PostMapping
    public ResponseEntity<Void> criarLoginTemporario(@RequestBody @Valid SolicitarLoginTemporarioDTO loginDTO) {
        return loginTemporarioService.criarLoginTemporario(loginDTO);

    }

    @PatchMapping("/{id}/utilizar")
    public ResponseEntity<Void> marcarComoUtilizado(@PathVariable Long id) {
        loginTemporarioService.marcarComoUtilizado(id);
        return ResponseEntity.ok().build();



    }

}