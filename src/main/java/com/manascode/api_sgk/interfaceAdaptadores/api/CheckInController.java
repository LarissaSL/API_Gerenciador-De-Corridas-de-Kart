package com.manascode.api_sgk.interfaceAdaptadores.api;

import com.manascode.api_sgk.aplicacao.check.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/check-in")
public class CheckInController {

    @Autowired
    private CheckService service;

    @PostMapping
    @Transactional
    public ResponseEntity criarCheckIn(@RequestBody @Valid CriarCheckInDTO dados) {
        return service.cadastrar(dados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalharCheckDTO> detalharCheckIn(@PathVariable Long id) {
        return service.detalhar(id);
    }

    @GetMapping
    public ResponseEntity<Page<ListarCheckInDTO>>  listarTodosComFiltros (
            @PageableDefault(size = 10, sort = {"inscricao.dataInscricao"}) Pageable paginacao,
            @RequestParam(required = false) Long id_corrida) {

        return service.listarTodosComFiltros(paginacao, id_corrida);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirCheckIn(@PathVariable Long id) {
        return service.excluir(id);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarCheckIn(@RequestBody @Valid AtualizarCheckInDTO dados) {
        return service.atualizar(dados);
    }

    @GetMapping("/qtdPorCorrida/{idCorrida}")
    public int mostrarQtdDeCheckInPorCorrida(@PathVariable Long idCorrida) {
        return service.contarCheckInsPorCorrida(idCorrida);
    }

}
