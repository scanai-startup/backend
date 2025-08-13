package com.scanai.api.services;

import com.scanai.api.domain.vinculodepositopedecuba.dto.DadosCadastroVinculoDepositoPedecuba;
import com.scanai.api.domain.vinculodepositopedecuba.dto.DadosDetalhamentoVinculoDepositoPedecuba;

public interface VinculoDepositoPeDeCubaServiceInterface {
    DadosDetalhamentoVinculoDepositoPedecuba vincularDepositoPedecuba(DadosCadastroVinculoDepositoPedecuba dados);
}
