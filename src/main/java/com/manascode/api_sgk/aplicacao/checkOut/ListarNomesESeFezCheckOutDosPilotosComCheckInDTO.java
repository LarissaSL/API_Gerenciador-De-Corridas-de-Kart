package com.manascode.api_sgk.aplicacao.checkOut;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.manascode.api_sgk.aplicacao.inscricao.DetalharInscricaoUsuarioDTO;

@JsonPropertyOrder({"id_check_in", "usuario", "check_out_feito"})
public record ListarNomesESeFezCheckOutDosPilotosComCheckInDTO(
        @JsonProperty("id_check_in")
        Long idCheckIn,
        DetalharInscricaoUsuarioDTO usuario,

        @JsonProperty("check_out_feito")
        Boolean checkOut) {
}
