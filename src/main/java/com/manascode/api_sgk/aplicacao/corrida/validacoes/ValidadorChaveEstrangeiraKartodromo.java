package com.manascode.api_sgk.aplicacao.corrida.validacoes;

import com.manascode.api_sgk.aplicacao.corrida.AtualizarCorridaDTO;
import com.manascode.api_sgk.aplicacao.corrida.CriarCorridaDTO;
import com.manascode.api_sgk.dominio.kartodromo.Kartodromo;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.CorridaException;
import com.manascode.api_sgk.infraestrutura.persistencia.KartodromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorChaveEstrangeiraKartodromo implements IValidadorCorrida {

    @Autowired
    private KartodromoRepository repositorio;

    @Override
    public void validar(CriarCorridaDTO dados) {
        verificar(dados.kartodromo_id());
    }

    @Override
    public void validar(AtualizarCorridaDTO dados) {
        return;
    }

    public void verificar(Long idKartodromo) {
        Kartodromo kartodromo = repositorio.getReferenceById(idKartodromo);

        if (kartodromo == null) {
            throw new CorridaException("Kartodromo não existe no sistema.");
        }

        if (!kartodromo.isAtivo()) {
            throw new CorridaException("Kartodromo precisa estar ativo no sistema.");
        }
    }
}
