package com.manascode.api_sgk.infraestrutura.excecao;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ExcecaoService {

    public String extrairMensagemDeErroChaveDuplicada(String mensagem) {
        List<String> listaDeErros = new ArrayList<>();
        listaDeErros.add("usuarios.cpf");
        listaDeErros.add("usuarios.telefone");
        listaDeErros.add("usuarios.email");
        listaDeErros.add("corrida.codigo");

        for (String campo : listaDeErros) {
            if (mensagem.contains(campo)) {
                return String.format("O valor informado para o campo '%s' já está em uso. Por favor, insira um valor diferente.", campo.split("\\.")[1]);
            }
        }

        return "Um valor duplicado foi encontrado no sistema. Por favor, verifique os dados inseridos.";
    }

    public String extrairEntidadeQueNaoFoiEncontrada(String mensagemDeErro) {
        Pattern pattern = Pattern.compile("Unable to find (.+?) with id");
        Matcher matcher = pattern.matcher(mensagemDeErro);

        if (matcher.find()) {
            // Captura o nome completo da entidade
            String entidadeCompleta = matcher.group(1);

            // Extrai o nome da entidade depois do ultimo .
            int ultimoPontoIndex = entidadeCompleta.lastIndexOf('.');
            if (ultimoPontoIndex != -1 && ultimoPontoIndex < entidadeCompleta.length() - 1) {
                return entidadeCompleta.substring(ultimoPontoIndex + 1);
            } else {
                return entidadeCompleta;
            }
        } else {
            return " um dos dados fornecidos";
        }
    }

     public String extrairValoresEsperadosDosEnums(String mensagemDeErro) {
        String valoresEsperados = "";

        // Expressão regular para capturar os valores esperados dos enums
        Pattern pattern = Pattern.compile("not one of the values accepted for Enum class: \\[([^\\]]+)\\]");
        Matcher matcher = pattern.matcher(mensagemDeErro);

        if (matcher.find()) {
            valoresEsperados = matcher.group(1);
        }

        return valoresEsperados;
    }



}
