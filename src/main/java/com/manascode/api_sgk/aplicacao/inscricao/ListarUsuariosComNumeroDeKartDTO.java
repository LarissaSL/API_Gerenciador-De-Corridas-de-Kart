package com.manascode.api_sgk.aplicacao.inscricao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manascode.api_sgk.aplicacao.usuario.DetalharNomeESobrenomeUsuarioProjecao;

import java.util.List;

public record ListarUsuariosComNumeroDeKartDTO(
        @JsonProperty("total_usuario_com_check_in")
        int totalUsuariosComCheckIn,
        @JsonProperty("total_usuario_com_numero_de_kart")
        int totalUsuariosComNumeroDeKart,
        @JsonProperty("usuario")
        List<DetalharNomeESobrenomeUsuarioProjecao> usuariosComNumeroDeKart) {
}
