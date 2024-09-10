package com.manascode.api_sgk.aplicacao.inscricao;

import com.manascode.api_sgk.aplicacao.inscricao.validacoes.IValidadorInscricao;
import com.manascode.api_sgk.dominio.corrida.Corrida;
import com.manascode.api_sgk.dominio.inscricao.Inscricao;
import com.manascode.api_sgk.dominio.inscricao.StatusPagamento;
import com.manascode.api_sgk.dominio.usuario.Usuario;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.InscricaoException;
import com.manascode.api_sgk.infraestrutura.persistencia.CorridaRepository;
import com.manascode.api_sgk.infraestrutura.persistencia.InscricaoRepository;
import com.manascode.api_sgk.infraestrutura.persistencia.UsuarioRepository;
import com.manascode.api_sgk.interfaceAdaptadores.mapper.InscricaoMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class InscricaoService {

    @Autowired
    private InscricaoRepository repositorio;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CorridaRepository corridaRepository;

    @Autowired
    private InscricaoMapper inscricaoMapper;

    @Autowired
    private List<IValidadorInscricao> validadores;

    public ResponseEntity fazerInscricao(@Valid CriarInscricaoDTO dados) {
        // Validacoes para Inscricao
        validadores.forEach(v -> v.validar(dados));

        Corrida corrida = corridaRepository.getReferenceById(dados.corridaId());
        Usuario usuario = usuarioRepository.getReferenceById(dados.usuarioId());

        // Criando a Inscricao pelo DTO
        Inscricao inscricao = inscricaoMapper.converteCriarInscricaoDtoEmInscricao(
                dados,
                corrida,
                usuario);

        // Setando alguns campos
        if (inscricao.getStatusPagamento() == null) {
            inscricao.setStatusPagamento(StatusPagamento.pendente);
        }

        inscricao.setDataInscricao(LocalDateTime.now());

        // Salvando no Banco e Exibindo de resposta o detalhe da Inscricao
        Inscricao inscricaoSalva = repositorio.save(inscricao);

        DetalharInscricaoDTO inscricaoDetalhada = inscricaoMapper
                .converteInscricaoEmDetalharInscricaoDto(inscricaoSalva);

        var uri = UriComponentsBuilder
                .fromPath("/inscricao/{id}")
                .buildAndExpand(inscricaoSalva.getId())
                .toUri();

        return ResponseEntity.created(uri).body(inscricaoDetalhada);
    }

    public ResponseEntity<DetalharInscricaoDTO> detalhar(Long id) {
        Inscricao inscricaoSalva = repositorio.findByIdAndAtivo(id, true);

        if (inscricaoSalva == null) {
            throw new InscricaoException("Inscrição não encontrada ou não está ativa.");
        }

        DetalharInscricaoDTO inscricaoDetalhada = inscricaoMapper.converteInscricaoEmDetalharInscricaoDto(inscricaoSalva);

        return ResponseEntity.ok(inscricaoDetalhada);
    }

    public ResponseEntity<Page<ListarInscricaoDTO>> listarTodos(@PageableDefault(size = 10, sort = {"corrida.id"}) Pageable paginacao) {
        // Pagina todos que estao Ativos
        Page<Inscricao> page = repositorio.findAllByAtivoTrue(paginacao);

        // Mapeia a Inscricao para ListarCorridaDTO
        Page<ListarInscricaoDTO> listaDeInscricoesDTO = page.map(inscricaoMapper::converteInscricaoEmListarCorridaDto);

        return ResponseEntity.ok(listaDeInscricoesDTO);
    }

    public ResponseEntity<Page<ListarInscricaoDTO>> listarTodosPorCorridaID(Long idCorrida, Pageable paginacao) {
        // Pagina todos pelo Id da corrida e que o status da corrida nao seja cancelado
        Page<Inscricao> page = repositorio.findByCorridaIdAndStatusPagamentoNot(idCorrida, StatusPagamento.cancelado, paginacao);

        // Mapeia a Inscricao para ListarCorridaDTO
        Page<ListarInscricaoDTO> listarInscricaoDTO = page.map(inscricaoMapper::converteInscricaoEmListarCorridaDto);
        return ResponseEntity.ok(listarInscricaoDTO);
    }


    public ResponseEntity<Void> excluir(Long id) {
        // Busca a inscrição ativa
        Inscricao inscricao = repositorio.findByIdAndAtivo(id, true);
        if (inscricao == null) {
            throw new InscricaoException("Inscrição não encontrada ou não está ativa.");
        }

        // Verifica a diferença de dias entre a data de inscrição e a data atual
        LocalDateTime dataInscricao = inscricao.getDataInscricao();
        LocalDateTime dataAtual = LocalDateTime.now();
        long qtdDeDias = ChronoUnit.DAYS.between(dataInscricao, dataAtual);

        // Verificacoes para devolução do pagamento
        if (inscricao.getStatusPagamento() == StatusPagamento.pago && qtdDeDias <= 7) {
            throw new InscricaoException("Entre em contato com o Administrador para o cancelamento da inscrição e devolução do pagamento.");
        }

        if (inscricao.getStatusPagamento() == StatusPagamento.pago && qtdDeDias > 7) {
            throw new InscricaoException("Não é possivel realizar a devolução do dinheiro, pois o prazo de 7 dias já passou.");
        }

        inscricao.excluir();

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity atualizar(@Valid AtualizarInscricaoDTO dados) {
        // Recuperar o Inscricao existente
        Inscricao inscricaoSalva = repositorio.findByIdAndAtivo(dados.id(), true);
        if (inscricaoSalva == null) {
            throw new InscricaoException("Inscrição não encontrada ou não está ativa.");
        }

        // Validar os dados
        validadores.forEach(v -> v.validar(dados));

        // Atualizar os dados de Inscrição
        inscricaoSalva.atualizar(dados);

        if (dados.usuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(dados.usuarioId())
                    .orElseThrow(() -> new InscricaoException("Usuário não encontrado."));

            inscricaoSalva.setUsuario(usuario);
        }

        if (dados.corridaId() != null) {
            Corrida corrida = corridaRepository.findById(dados.corridaId())
                    .orElseThrow(() -> new InscricaoException("Corrida não encontrada."));

            inscricaoSalva.setCorrida(corrida);
        }

        // Exibir as novas informações da Inscrição
        DetalharInscricaoDTO inscricaoDetalhada = inscricaoMapper.converteInscricaoEmDetalharInscricaoDto(inscricaoSalva);

        return ResponseEntity.ok(inscricaoDetalhada);
    }
}
