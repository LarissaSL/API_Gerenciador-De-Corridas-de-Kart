package com.manascode.api_sgk.interfaceAdaptadores.api;

import com.manascode.api_sgk.aplicacao.inscricao.ListarUsuariosComNumeroDeKartDTO;
import com.manascode.api_sgk.aplicacao.sorteador.ConfiguracoesDoSorteioDTO;
import com.manascode.api_sgk.aplicacao.sorteador.ListarUsuariosParaSorteioDTO;
import com.manascode.api_sgk.aplicacao.sorteador.SorteadorService;
import com.manascode.api_sgk.aplicacao.sorteador.DetalharInformacaoGeralDoSorteioDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sorteador")
public class SorteadorController {

    @Autowired
    private SorteadorService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalharInformacaoGeralDoSorteioDTO> realizarSorteio(@Valid @RequestBody ConfiguracoesDoSorteioDTO configuracoes) {
         return service.realizarSorteioDeNumerosDeKart(configuracoes);
    }

    @GetMapping
    public ResponseEntity<Page<ListarUsuariosParaSorteioDTO>> listarNomeESobrenomeParaSorteio(
            @PageableDefault(size = 10) Pageable paginacao,
            @RequestParam(required = true) Long id_corrida) {

        return service.listarUsuariosParaSorteio(paginacao, id_corrida);
    }

    @GetMapping("/verificar")
    public ResponseEntity<ListarUsuariosComNumeroDeKartDTO> listarUsuariosComNumeroDeKart(
            @RequestParam(required = true) Long id_corrida) {

        return service.listarUsuariosComNumeroDeKartPorIdCorrida(id_corrida);
    }

    @DeleteMapping("/{idCorrida}")
    @Transactional
    public ResponseEntity excluirSorteiosDeKartPorIdCorrida(@PathVariable Long idCorrida) {
        return service.excluir(idCorrida);
    }
}
