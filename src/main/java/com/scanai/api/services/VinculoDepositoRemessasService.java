package com.scanai.api.services;

import com.scanai.api.domain.depositomostro.dto.DadosCadastroDepositoMostro;
import com.scanai.api.domain.depositomostro.DepositoMostro;
import com.scanai.api.domain.mostro.Mostro;
import com.scanai.api.domain.mostro.dto.DadosCadastroMostro;
import com.scanai.api.domain.vinculodepositoremessas.dto.DadosCadastroVinculoDepositoRemessas;
import com.scanai.api.domain.vinculodepositoremessas.dto.DadosDetalhamentoVinculoDepositoRemessas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VinculoDepositoRemessasService {

    @Autowired
    private UvaService _uvaService;

    @Autowired
    private MostroService _mostroService;

    @Autowired
    private DepositoMostroService _depositoMostroService;

    public DadosDetalhamentoVinculoDepositoRemessas vincularDepositoRemessa(DadosCadastroVinculoDepositoRemessas data) {
        // criar mostro
        Mostro mostro = _mostroService.register(new DadosCadastroMostro(data.funcionarioId(), 12));

        // vincular o mostro às remessas
        for (Long remessaUvaId : data.remessaUvaIdList()) {
            _uvaService.addFkMostro(remessaUvaId, mostro.getId());
        }

        // vincular mostro ao deposito
        DepositoMostro depositoMostro = _depositoMostroService.register(new DadosCadastroDepositoMostro(mostro.getId(), data.depositoId(), LocalDate.now(), data.funcionarioId()));

        return new DadosDetalhamentoVinculoDepositoRemessas(data.depositoId(), mostro.getId(), depositoMostro.getId(), data.funcionarioId(), data.remessaUvaIdList());
    }
}
