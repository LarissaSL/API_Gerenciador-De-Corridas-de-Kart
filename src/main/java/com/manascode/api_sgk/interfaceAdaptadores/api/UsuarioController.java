package com.manascode.api_sgk.interfaceAdaptadores.api;

import com.manascode.api_sgk.aplicacao.usuario.AtualizarUsuarioDTO;
import com.manascode.api_sgk.aplicacao.usuario.CriarUsuarioDTO;
import com.manascode.api_sgk.aplicacao.usuario.ListarUsuarioDTO;
import com.manascode.api_sgk.aplicacao.usuario.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    @Transactional
    public ResponseEntity criarUsuario(@RequestBody @Valid CriarUsuarioDTO dados) {
        return service.cadastrar(dados);
    }

    @GetMapping
    public ResponseEntity<Page<ListarUsuarioDTO>> listarTodos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return service.listarTodos(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalherUsuario(@PathVariable Long id) {
        return service.detalhar(id);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarUsuario(@RequestBody @Valid AtualizarUsuarioDTO dados) {
        return service.atualizar(dados);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirUsuario(@PathVariable Long id) {
        return service.excluir(id);
    }
}
