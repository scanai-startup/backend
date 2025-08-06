package com.scanai.api.services;

import com.scanai.api.domain.depositomostro.DepositoMostro;
import com.scanai.api.domain.depositomostro.dto.DadosCadastroDepositoMostro;
import com.scanai.api.domain.depositomostro.dto.DadosTrasfegaDepositoMostro;
import com.scanai.api.domain.mostro.Mostro;

public interface DepositoMostroServiceInterface {

    DepositoMostro register(DadosCadastroDepositoMostro data);

    DepositoMostro trasfegaMostro(DadosTrasfegaDepositoMostro data);

    DepositoMostro findDepositoMostroByFkdepositoFkmostro(Long fkdeposito, Long fkmostro);

    void softDelete(Long fkdeposito, Long fkmostro);

    void setDataFim(Long fkmostro);
}
