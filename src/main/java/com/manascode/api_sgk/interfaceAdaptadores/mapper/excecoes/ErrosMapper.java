package com.manascode.api_sgk.interfaceAdaptadores.mapper.excecoes;

import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CampoValidacaoException;
import com.manascode.api_sgk.infraestrutura.excecao.ResponseErrorPadraoRFC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class ErrosMapper {

    @Autowired
    private ExcecoesMapper excecoesMapper;

    public ResponseErrorPadraoRFC mapearVariosErros(List<CampoValidacaoException> excecoes, String uri, String titulo, HttpStatus status) {
        String detalhes = excecoes.stream()
                .map(CampoValidacaoException::getMessage)
                .collect(Collectors.joining(" | "));

        return excecoesMapper.converteExcecaoParaDtoRFC(
                titulo,
                detalhes,
                status,
                uri
        );
    }
}