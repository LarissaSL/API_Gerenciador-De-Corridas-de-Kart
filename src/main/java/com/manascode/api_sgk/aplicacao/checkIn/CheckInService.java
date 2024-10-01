package com.manascode.api_sgk.aplicacao.checkIn;

import com.manascode.api_sgk.aplicacao.checkIn.validacoes.IValidadorCheckIn;
import com.manascode.api_sgk.dominio.check.Check;
import com.manascode.api_sgk.dominio.corrida.Corrida;
import com.manascode.api_sgk.dominio.inscricao.Inscricao;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CheckException;
import com.manascode.api_sgk.infraestrutura.persistencia.CheckRepository;
import com.manascode.api_sgk.infraestrutura.persistencia.CorridaRepository;
import com.manascode.api_sgk.infraestrutura.persistencia.InscricaoRepository;
import com.manascode.api_sgk.interfaceAdaptadores.mapper.CheckMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CheckInService {

    @Autowired
    private CheckRepository repositorio;

    @Autowired
    private CheckMapper checkMapper;

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private CorridaRepository corridaRepository;

    @Autowired
    private MensagemWhatsappService mensagemWhatsappService;

    @Autowired
    private List<IValidadorCheckIn> validadorCheckIns;


    public ResponseEntity<DetalharCheckDTO> cadastrar(@Valid CriarCheckInDTO dados) {
        validadorCheckIns.forEach(v -> v.validar(dados));

        Inscricao inscricao = inscricaoRepository.getReferenceById(dados.idInscricao());

        Check checkIn = checkMapper.converteCriarCheckInDtoEmCheck(dados, inscricao);

        checkIn.setDataCheckin(LocalDateTime.now());
        Check checkInSalvo = repositorio.save(checkIn);

        DetalharCheckDTO detalharCheckInDTO = checkMapper.converterCheckInEmDetalharCheckInDto(checkInSalvo);

        var uri = UriComponentsBuilder
                .fromPath("/check-in/{id}")
                .buildAndExpand(checkInSalvo.getId())
                .toUri();

        return ResponseEntity.created(uri).body(detalharCheckInDTO);

    }

    public ResponseEntity<DetalharCheckDTO> detalhar(Long id) {
        Check checkInSalvo = repositorio.encontrarPorInscricao(id);

        if (checkInSalvo == null) {
            throw new CheckException("Check-in não encontrado.");
        }

        DetalharCheckDTO checkInDetalhado = checkMapper.converterCheckInEmDetalharCheckInDto(checkInSalvo);

        return ResponseEntity.ok(checkInDetalhado);
    }

    public ResponseEntity<Page<ListarCheckInDTO>> listarTodosComFiltros(Pageable paginacao, Long idCorrida) {
        Page<Check> page = repositorio.listarCheckPorFiltrosIdCorrida(paginacao, idCorrida);
        Page<ListarCheckInDTO> listarCheckInDTOS = page.map(checkMapper::converterCheckEmListarCheckInDto);

        return ResponseEntity.ok(listarCheckInDTOS);
    }

    public ResponseEntity excluir(Long id) {
        Check checkInSalvo = repositorio.encontrarPorId(id);

        if (checkInSalvo == null) {
            throw new CheckException("Check-in não encontrado.");
        }

        repositorio.delete(checkInSalvo);

        return ResponseEntity.noContent().build();
    }


    public ResponseEntity atualizar(AtualizarCheckInDTO dados) {
        Check checkInSalvo = repositorio.encontrarPorInscricao(dados.idInscricao());

        if (checkInSalvo == null) {
            throw new CheckException("Check-in não encontrado.");
        }

        // Validando as novas informações
        validadorCheckIns.forEach(v -> v.validar(dados));

        // Atualizando
        checkInSalvo.atualizarCheckIn(dados);

        // Exibir novas informações
        DetalharCheckDTO checkInDetalhado = checkMapper.converterCheckInEmDetalharCheckInDto(checkInSalvo);

        return ResponseEntity.ok(checkInDetalhado);
    }

    public int contarCheckInsPorCorrida(Long idCorrida) {
        return repositorio.contarCheckInsPorIdCorrida(idCorrida);
    }

    public ResponseEntity<String> compartilharCheckInPorWhatsapp(Long idCorrida) {
        Corrida corrida = corridaRepository.findByIdAndAtivo(idCorrida, true);

        if (corrida == null) {
            throw new CheckException("Não é possivel compartilhar o check-in de uma corrida que não existe ou não está ativa.");
        }

        List<CompartilharCheckInProjecao> listaDeCheckInsDaCorrida = repositorio.listarCheckInPorIdCorrida(idCorrida);

        if (listaDeCheckInsDaCorrida.size() == 0) {
            throw new CheckException("Nenhum check-in feito para essa corrida.");
        }

        String nomeCorrida = corrida.getNome();
        String classificacaoCorrida = corrida.getClassificacao().name();
        String categoriaCorrida = corrida.getCategoria();

        String mensagemFormatada = mensagemWhatsappService
                .gerarMensagem(listaDeCheckInsDaCorrida, nomeCorrida, categoriaCorrida, classificacaoCorrida);

        return ResponseEntity.ok(mensagemFormatada);
    }
}
