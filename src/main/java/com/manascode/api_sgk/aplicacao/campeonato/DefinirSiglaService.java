package com.manascode.api_sgk.aplicacao.campeonato;

import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CampeonatoException;
import org.springframework.stereotype.Service;

@Service
public class DefinirSiglaService {

    public String definirSigla(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new CampeonatoException("O nome do campeonato não pode ser nulo ou vazio.");
        }

        nome = nome.toLowerCase();

        if (nome.contains("ddl") || nome.contains("desafio dos loucos")) {
            return "DDL";
        } else if (nome.contains("ckc") || nome.contains("crash kart championship") || nome.contains("crash kart")) {
            return "CKC";
        } else {
            return "Não é possivel criar campeonatos além dos definidos nas regras do negócio.";
        }
    }
}
