package com.scanai.api.services;

import com.scanai.api.domain.vinculodepositovinho.dto.DadosCadastroVinculoDepositoVinho;
import com.scanai.api.domain.vinculodepositovinho.dto.DadosDetalhamentoVinculoDepositoVinho;

public interface VinculoDepositoVinhoServiceInterface {

    DadosDetalhamentoVinculoDepositoVinho vincularDepositoVinho(DadosCadastroVinculoDepositoVinho dados);

}
