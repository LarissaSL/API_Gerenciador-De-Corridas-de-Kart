package com.manascode.api_sgk.aplicacao.corrida;

import com.manascode.api_sgk.infraestrutura.persistencia.CampeonatoRepository;
import com.manascode.api_sgk.infraestrutura.persistencia.CorridaRepository;
import com.manascode.api_sgk.infraestrutura.persistencia.KartodromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CorridaService {

    @Autowired
    private CorridaRepository repositorio;

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    @Autowired
    private KartodromoRepository kartodromoRepository;

    public ResponseEntity cadastrar() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity atualizar() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity detalhar() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity listarTodos() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity excluir() {
        return ResponseEntity.ok().build();
    }
}
