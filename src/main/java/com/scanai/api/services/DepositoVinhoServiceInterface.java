package com.scanai.api.services;

import com.scanai.api.domain.depositovinho.Depositovinho;
import com.scanai.api.domain.depositovinho.dto.DadosCadastroDepositoVinho;
import com.scanai.api.domain.depositovinho.dto.DadosTrasfegaDepositoVinho;

public interface DepositoVinhoServiceInterface {

    Depositovinho register(DadosCadastroDepositoVinho data);

    Depositovinho trasfegaVinho(DadosTrasfegaDepositoVinho data);

    void setDataFim(Long fkvinho);
}
