package com.scanai.api.domain.vinculodepositovinho.dto;

import java.util.List;

public record DadosDetalhamentoVinculoDepositoVinho (Long vinhoId, Float vinhoVolume, Long depositoId, Long pedecubaId, Long rotuloId, Long mostroId, String message){
}
