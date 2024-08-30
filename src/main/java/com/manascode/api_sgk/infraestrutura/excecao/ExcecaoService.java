package com.manascode.api_sgk.infraestrutura.excecao;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExcecaoService {
    String extrairMensagemDeErroChaveDuplicada(String mensagem) {
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
