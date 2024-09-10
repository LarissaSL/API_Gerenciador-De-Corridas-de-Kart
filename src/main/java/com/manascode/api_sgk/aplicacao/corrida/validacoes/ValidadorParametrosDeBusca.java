package com.manascode.api_sgk.aplicacao.corrida.validacoes;

import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CorridaException;
import org.springframework.stereotype.Service;

@Service
public class ValidadorParametrosDeBusca {

    public Integer validarMes(String mes) throws CorridaException {
        if (mes != null) {
            try {
                Integer mesInt = Integer.parseInt(mes);
                if (mesInt < 1 || mesInt > 12) {
                    throw new CorridaException("O mês deve estar entre 1 e 12.");
                }
                return mesInt;
            } catch (NumberFormatException e) {
                throw new CorridaException("O mês deve ser um número válido.");
            }
        }
        return null;
    }

    public Integer validarDia(String dia) throws CorridaException {
        if (dia != null) {
            try {
                Integer diaInt = Integer.parseInt(dia);
                if (diaInt < 1 || diaInt > 31) {
                    throw new CorridaException("O dia deve estar entre 1 e 31.");
                }
                return diaInt;
            } catch (NumberFormatException e) {
                throw new CorridaException("O dia deve ser um número válido.");
            }
        }
        return null;
    }
}
