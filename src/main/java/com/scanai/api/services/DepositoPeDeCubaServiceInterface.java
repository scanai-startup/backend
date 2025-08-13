package com.scanai.api.services;

import com.scanai.api.domain.depositopedecuba.Depositopedecuba;
import com.scanai.api.domain.depositopedecuba.dto.DadosCadastroDepositoPeDeCuba;
import com.scanai.api.domain.depositopedecuba.dto.DadosDetalhamentoDepositoPeDeCuba;
import com.scanai.api.domain.depositopedecuba.dto.DadosTrasfegaDepositoPeDeCuba;

public interface DepositoPeDeCubaServiceInterface {

    DadosDetalhamentoDepositoPeDeCuba register(DadosCadastroDepositoPeDeCuba data);

    DadosDetalhamentoDepositoPeDeCuba trasfegaPedecuba(DadosTrasfegaDepositoPeDeCuba data);

    void setDataFim(Long fkpedecuba);

}
