package com.scanai.api.services;

import com.scanai.api.domain.depositopedecuba.Depositopedecuba;
import com.scanai.api.domain.depositopedecuba.dto.DadosCadastroDepositoPeDeCuba;
import com.scanai.api.domain.depositopedecuba.dto.DadosTrasfegaDepositoPeDeCuba;

public interface DepositoPeDeCubaServiceInterface {

    Depositopedecuba register(DadosCadastroDepositoPeDeCuba data);

    Depositopedecuba trasfegaPedecuba(DadosTrasfegaDepositoPeDeCuba data);

    void setDataFim(Long fkpedecuba);
}
