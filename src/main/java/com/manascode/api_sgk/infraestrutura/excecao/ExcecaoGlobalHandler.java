package com.manascode.api_sgk.infraestrutura.excecao;

import com.manascode.api_sgk.interfaceAdaptadores.mapper.excecoes.ExcecoesMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExcecaoGlobalHandler {

    // Erros em relação aos Campos
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErrorPadraoRFC>  tratarErroValidacao(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String uri = request.getRequestURI();
        String titulo = "Campos de Entrada com erros";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        String detalhes = ex.getBindingResult().getFieldErrors().stream()
                .map(erro -> erro.getDefaultMessage())
                .collect(Collectors.joining(" | "));

        ResponseErrorPadraoRFC problema = ExcecoesMapper.INSTANCE.converteExcecaoParaDtoRFC(
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
            detalhes = extrairMensagemDeErroChaveDuplicada(ex.getMessage());
        } else {
            detalhes = ex.getMessage();
        }

        ResponseErrorPadraoRFC problema = ExcecoesMapper.INSTANCE.converteExcecaoParaDtoRFC(
                titulo,
                detalhes,
                status,
                uri
        );

        return ResponseEntity.status(status).body(problema);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseErrorPadraoRFC>  tratarErroEntidadeNaoEncontrada (HttpServletRequest request) {
        String uri = request.getRequestURI();
        String titulo = "Erro no Banco de Dados";
        HttpStatus status = HttpStatus.NOT_FOUND;

        ResponseErrorPadraoRFC problema = ExcecoesMapper.INSTANCE.converteExcecaoParaDtoRFC(
                titulo,
                "Não foi possivel encontrar esse registro",
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

        ResponseErrorPadraoRFC problema = ExcecoesMapper.INSTANCE.converteExcecaoParaDtoRFC(
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

        ResponseErrorPadraoRFC problema = ExcecoesMapper.INSTANCE.converteExcecaoParaDtoRFC(
                titulo,
                excecao.getMessage(),
                status,
                uri
        );

        return ResponseEntity.status(status).body(problema);
    }


    private String extrairMensagemDeErroChaveDuplicada(String mensagem) {
        List<String> listaDeErros = new ArrayList<>();
        listaDeErros.add("usuarios.cpf");
        listaDeErros.add("usuarios.telefone");
        listaDeErros.add("usuarios.email");

        for (String campo : listaDeErros) {
            if (mensagem.contains(campo)) {
                return String.format("O valor informado para o campo '%s' já está em uso. Por favor, insira um valor diferente.", campo.split("\\.")[1]);
            }
        }

        return "Um valor duplicado foi encontrado no sistema. Por favor, verifique os dados inseridos.";
    }

}
