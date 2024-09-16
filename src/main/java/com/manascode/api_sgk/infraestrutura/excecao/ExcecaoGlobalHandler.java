package com.manascode.api_sgk.infraestrutura.excecao;

import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.*;
import com.manascode.api_sgk.interfaceAdaptadores.mapper.excecoes.ExcecoesMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExcecaoGlobalHandler {

    @Autowired
    private ExcecoesMapper excecoesMapper;

    @Autowired
    private ExcecaoService excecaoService;

    // Erros em relação aos Campos
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErrorPadraoRFC>  tratarErroValidacao(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String uri = request.getRequestURI();
        String titulo = "Campos de Entrada com erros";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        String detalhes = ex.getBindingResult().getFieldErrors().stream()
                .map(erro -> erro.getDefaultMessage())
                .collect(Collectors.joining(" | "));

        ResponseErrorPadraoRFC problema = excecoesMapper.converteExcecaoParaDtoRFC(
                titulo,
                detalhes,
                status,
                uri
        );

        return ResponseEntity.status(status).body(problema);
    }

    // Erros em relação ao Banco
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ResponseErrorPadraoRFC>  ResponseEntitytratarErrosDoBanco(SQLException ex, HttpServletRequest request) {
        String uri = request.getRequestURI();
        String titulo = "Erro no Banco de Dados";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String detalhes;

        // Verifica se é um erro de chave duplicada
        if (ex instanceof SQLException && ex.getErrorCode() == 1062) {
            detalhes = excecaoService.extrairMensagemDeErroChaveDuplicada(ex.getMessage());
        } else {
            detalhes = ex.getMessage();
        }

        ResponseErrorPadraoRFC problema = excecoesMapper.converteExcecaoParaDtoRFC(
                titulo,
                detalhes,
                status,
                uri
        );

        return ResponseEntity.status(status).body(problema);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseErrorPadraoRFC> tratarErroEntidadeNaoEncontrada(HttpServletRequest request, EntityNotFoundException excecao) {
        String uri = request.getRequestURI();
        String titulo = "Erro nos Registros do Banco de Dados";
        HttpStatus status = HttpStatus.NOT_FOUND;

        // Extraindo a Entidade para melhor direcionamento do Usuario
        String entidadeNaoEncontrada = excecaoService.extrairEntidadeQueNaoFoiEncontrada(excecao.getMessage());
        String mensagemPersonalizada = String.format("Não foi possível encontrar o registro de %s.", entidadeNaoEncontrada);

        ResponseErrorPadraoRFC problema = excecoesMapper.converteExcecaoParaDtoRFC(
                titulo,
                mensagemPersonalizada,
                status,
                uri
        );

        return ResponseEntity.status(status).body(problema);
    }

    @ExceptionHandler(UsuarioException.class)
    public ResponseEntity<ResponseErrorPadraoRFC> tratarErroDeValidacoesUsuario (HttpServletRequest request, UsuarioException excecao) {
        String uri = request.getRequestURI();
        String titulo = "Erro nos dados de usuário";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ResponseErrorPadraoRFC problema = excecoesMapper.converteExcecaoParaDtoRFC(
                titulo,
                excecao.getMessage(),
                status,
                uri
        );

        return ResponseEntity.status(status).body(problema);
    }

    @ExceptionHandler(KartodromoException.class)
    public ResponseEntity<ResponseErrorPadraoRFC> tratarErroDeValidacoesKartodromo (HttpServletRequest request, KartodromoException excecao) {
        String uri = request.getRequestURI();
        String titulo = "Erro nos dados de Kartodromo";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ResponseErrorPadraoRFC problema = excecoesMapper.converteExcecaoParaDtoRFC(
                titulo,
                excecao.getMessage(),
                status,
                uri
        );

        return ResponseEntity.status(status).body(problema);
    }

    @ExceptionHandler(CampeonatoException.class)
    public ResponseEntity<ResponseErrorPadraoRFC> tratarErroDeValidacoesCampeonato (HttpServletRequest request, CampeonatoException excecao) {
        String uri = request.getRequestURI();
        String titulo = "Erro nos dados de Campeonato";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ResponseErrorPadraoRFC problema = excecoesMapper.converteExcecaoParaDtoRFC(
                titulo,
                excecao.getMessage(),
                status,
                uri
        );

        return ResponseEntity.status(status).body(problema);
    }

    @ExceptionHandler(CorridaException.class)
    public ResponseEntity<ResponseErrorPadraoRFC> tratarErroDeValidacoesCorrida (HttpServletRequest request, CorridaException excecao) {
        String uri = request.getRequestURI();
        String titulo = "Erro nos dados da Corrida";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ResponseErrorPadraoRFC problema = excecoesMapper.converteExcecaoParaDtoRFC(
                titulo,
                excecao.getMessage(),
                status,
                uri
        );

        return ResponseEntity.status(status).body(problema);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseErrorPadraoRFC> tratarErrosDeDesserializacao (HttpServletRequest request, HttpMessageNotReadableException excecao) {
        String uri = request.getRequestURI();
        String titulo = "Erro nos dados";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        // Extraindo quais valores são esperados em algum Enum
        String mensagem = String.format("Valores esperados: %s",excecaoService.extrairValoresEsperadosDosEnums(excecao.getMessage()));

        ResponseErrorPadraoRFC problema = excecoesMapper.converteExcecaoParaDtoRFC(
                titulo,
                mensagem,
                status,
                uri
        );

        return ResponseEntity.status(status).body(problema);
    }

    @ExceptionHandler(AutenticacaoException.class)
    public ResponseEntity<ResponseErrorPadraoRFC> tratarErrosDeAutenticacao (HttpServletRequest request, AutenticacaoException excecao) {
        String uri = request.getRequestURI();
        String titulo = "Erro de Autenticação";
        HttpStatus status = excecao.getStatus();

        ResponseErrorPadraoRFC problema = excecoesMapper.converteExcecaoParaDtoRFC(
            titulo,
            excecao.getMessage(),
            status,
            uri
        );

        return ResponseEntity.status(status).body(problema);
    }

    @ExceptionHandler(InscricaoException.class)
    public ResponseEntity<ResponseErrorPadraoRFC> tratarErrosDeInscricaoNaCorrida (HttpServletRequest request, InscricaoException excecao) {
        String uri = request.getRequestURI();
        String titulo = "Erro de Inscrição";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ResponseErrorPadraoRFC problema = excecoesMapper.converteExcecaoParaDtoRFC(
                titulo,
                excecao.getMessage(),
                status,
                uri
        );

        return ResponseEntity.status(status).body(problema);
    }

    @ExceptionHandler(CheckException.class)
    public ResponseEntity<ResponseErrorPadraoRFC> tratarErrosDeCheckInECheckOut (HttpServletRequest request, CheckException excecao) {
        String uri = request.getRequestURI();
        String titulo = "Erro de Check-in/Check-out";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ResponseErrorPadraoRFC problema = excecoesMapper.converteExcecaoParaDtoRFC(
                titulo,
                excecao.getMessage(),
                status,
                uri
        );

        return ResponseEntity.status(status).body(problema);
    }

}
