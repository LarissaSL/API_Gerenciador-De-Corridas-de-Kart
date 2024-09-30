package com.manascode.api_sgk.interfaceAdaptadores.api;

import com.manascode.api_sgk.aplicacao.check.AtualizarCheckInDTO;
import com.manascode.api_sgk.aplicacao.check.CheckService;
import com.manascode.api_sgk.aplicacao.check.CriarCheckInDTO;
import com.manascode.api_sgk.aplicacao.check.DetalharCheckDTO;
import com.manascode.api_sgk.aplicacao.check.ListarCheckInDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{idInscricao}")
    public ResponseEntity<DetalharCheckDTO> detalharCheckIn(@PathVariable Long idInscricao) {
        return service.detalhar(idInscricao);
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

    @GetMapping("/compartilhar/{idCorrida}")
    public ResponseEntity<String> compartilhar(@PathVariable Long idCorrida) {
        return service.compartilharCheckInPorWhatsapp(idCorrida);
    }


}
