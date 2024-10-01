package com.manascode.api_sgk.aplicacao.checkOut;

import com.manascode.api_sgk.aplicacao.checkIn.DetalharCheckDTO;
import com.manascode.api_sgk.aplicacao.checkOut.validacoes.IValidadorCheckOut;
import com.manascode.api_sgk.dominio.check.Check;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CheckException;
import com.manascode.api_sgk.infraestrutura.persistencia.CheckRepository;
import com.manascode.api_sgk.interfaceAdaptadores.mapper.CheckMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckOutService {

    @Autowired
    private CheckRepository repositorio;

    @Autowired
    private CheckMapper checkMapper;

    @Autowired
    private List<IValidadorCheckOut> validadores;

    public ResponseEntity<Page<ListarNomesESeFezCheckOutDosPilotosComCheckInDTO>> listarTodosCheckInsPorIdCorrida(Pageable paginacao, Long idCorrida) {
        // Verificando se existem Check-ins para a Corrida
        int totalCheckInsPorIdCorrida = repositorio.contarCheckInsPorIdCorrida(idCorrida);

        if (totalCheckInsPorIdCorrida < 1) {
            throw new CheckException("Não tem Check-ins feitos para essa Corrida.");
        }

        // Preparando a Paginação para exibir uma Lista com os Check-ins da corrida
        Page<Check> page = repositorio.listarCheckPorFiltrosIdCorrida(paginacao, idCorrida);
        Page<ListarNomesESeFezCheckOutDosPilotosComCheckInDTO> listarCheckOutDTO = page.map(checkMapper::converteCheckEmListarNomesEPesoPilotosComCheckInDTO);

        return ResponseEntity.ok(listarCheckOutDTO);

    }

    public ResponseEntity fazerCheckOut(@Valid RealizarCheckOutDTO dados) {
        // Realizando as validações
        validadores.forEach(v -> v.validar(dados));

        // Buscando o CheckInSalvo no Banco
        Check checkInSalvo = repositorio.encontrarPorId(dados.idCheckIn());

        // Atualizando as informações para concluir o Check-out
        checkInSalvo.atualizarDadosParaCheckOut(dados);

        // Exibir novas informações
        DetalharCheckDTO checkInDetalhado = checkMapper.converterCheckInEmDetalharCheckInDto(checkInSalvo);

        return ResponseEntity.ok(checkInDetalhado);
    }
}
