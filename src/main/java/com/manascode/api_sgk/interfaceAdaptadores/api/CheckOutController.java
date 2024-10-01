package com.manascode.api_sgk.interfaceAdaptadores.api;

import com.manascode.api_sgk.aplicacao.checkIn.AtualizarCheckInDTO;
import com.manascode.api_sgk.aplicacao.checkOut.CheckOutService;
import com.manascode.api_sgk.aplicacao.checkOut.ListarNomesESeFezCheckOutDosPilotosComCheckInDTO;
import com.manascode.api_sgk.aplicacao.checkOut.RealizarCheckOutDTO;
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
@RequestMapping("/check-out")
public class CheckOutController {

    @Autowired
    private CheckOutService service;

    @GetMapping
    public ResponseEntity<Page<ListarNomesESeFezCheckOutDosPilotosComCheckInDTO>> listarTodosComFiltroIdCorrida (
            @PageableDefault(size = 10, sort = {"inscricao.dataInscricao"}) Pageable paginacao,
            @RequestParam(required = true) Long id_corrida) {

        return service.listarTodosCheckInsPorIdCorrida(paginacao, id_corrida);
    }

    @PutMapping
    @Transactional
    public ResponseEntity fazerCheckOut(@RequestBody @Valid RealizarCheckOutDTO dados) {
        return service.fazerCheckOut(dados);
    }



}
