package com.scanai.api.services;

import com.scanai.api.domain.vinculodepositoremessas.dto.DadosCadastroVinculoDepositoRemessas;
import com.scanai.api.domain.vinculodepositoremessas.dto.DadosDetalhamentoVinculoDepositoRemessas;

public interface VinculoDepositoRemessasServiceInterface {
    DadosDetalhamentoVinculoDepositoRemessas vincularDepositoRemessa(DadosCadastroVinculoDepositoRemessas data);
}
