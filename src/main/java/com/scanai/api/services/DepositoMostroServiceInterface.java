package com.scanai.api.services;

import com.scanai.api.domain.depositomostro.DepositoMostro;
import com.scanai.api.domain.depositomostro.dto.DadosCadastroDepositoMostro;
import com.scanai.api.domain.depositomostro.dto.DadosDetalhamentoDepositoMostro;
import com.scanai.api.domain.depositomostro.dto.DadosTrasfegaDepositoMostro;
import com.scanai.api.domain.mostro.Mostro;

public interface DepositoMostroServiceInterface {

    DadosDetalhamentoDepositoMostro register(DadosCadastroDepositoMostro data);

    DadosDetalhamentoDepositoMostro trasfegaMostro(DadosTrasfegaDepositoMostro data);

    DadosDetalhamentoDepositoMostro findDepositoMostroByFkdepositoFkmostro(Long fkdeposito, Long fkmostro);

    void softDelete(Long fkdeposito, Long fkmostro);

    void setDataFim(Long fkmostro);
}
