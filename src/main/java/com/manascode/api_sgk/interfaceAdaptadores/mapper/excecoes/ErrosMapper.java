package com.manascode.api_sgk.interfaceAdaptadores.mapper.excecoes;

import com.manascode.api_sgk.infraestrutura.excecao.CampoValidacaoException;
import com.manascode.api_sgk.infraestrutura.excecao.ResponseErrorPadraoRFC;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

public class ErrosMapper {
    public static ResponseErrorPadraoRFC mapearVariosErros(List<CampoValidacaoException> excecoes, String uri, String titulo, HttpStatus status) {
        String detalhes = excecoes.stream()
                .map(CampoValidacaoException::getMessage)
                .collect(Collectors.joining(" | "));

        return ExcecoesMapper.INSTANCE.converteExcecaoParaDtoRFC(
                titulo,
                detalhes,
                status,
                uri
        );
    }
}