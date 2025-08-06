package com.scanai.api.services.implement;

import com.scanai.api.domain.depositomostro.dto.DadosCadastroDepositoMostro;
import com.scanai.api.domain.depositomostro.DepositoMostro;
import com.scanai.api.domain.mostro.Mostro;
import com.scanai.api.domain.mostro.dto.DadosCadastroMostro;
import com.scanai.api.domain.vinculodepositoremessas.dto.DadosCadastroVinculoDepositoRemessas;
import com.scanai.api.domain.vinculodepositoremessas.dto.DadosDetalhamentoVinculoDepositoRemessas;
import com.scanai.api.services.DepositoMostroServiceInterface;
import com.scanai.api.services.MostroServiceInterface;
import com.scanai.api.services.UvaServiceInterface;
import com.scanai.api.services.VinculoDepositoRemessasServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

//TODO transformar em um useCase futuramente

@Service
public class VinculoDepositoRemessasService implements VinculoDepositoRemessasServiceInterface {

    @Autowired
    private UvaServiceInterface uvaService;

    @Autowired
    private MostroServiceInterface mostroService;

    @Autowired
    private DepositoMostroServiceInterface depositoMostroService;

    //TODO verificar mensagem tambem
    public DadosDetalhamentoVinculoDepositoRemessas vincularDepositoRemessa(DadosCadastroVinculoDepositoRemessas data) {
        Mostro mostro;
        String message;

        mostro = mostroService.register(new DadosCadastroMostro(data.funcionarioId(), data.volume(), null, null));
        for (Long remessaUvaId : data.remessaUvaIdList()) {
            uvaService.addFkMostro(remessaUvaId, mostro.getId());
            uvaService.softDelete(remessaUvaId);
        }
        DepositoMostro depositoMostro = depositoMostroService.register(new DadosCadastroDepositoMostro(mostro.getId(), data.depositoId(), LocalDate.now(), data.funcionarioId()));

        message = "Mostro criado e vinculado às remessas e ao depósito";

        return new DadosDetalhamentoVinculoDepositoRemessas(data.depositoId(), mostro.getId(), mostro.getVolume() ,data.funcionarioId(), data.remessaUvaIdList(), message);
    }
}
