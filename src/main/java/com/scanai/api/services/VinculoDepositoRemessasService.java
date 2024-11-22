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
        Mostro mostro = null;
        String message;
        
        //verifico se foi recebido mostroId na requisição
        if(data.mostroId() != null) {
            mostro = _mostroService.getElement(data.mostroId());

            if(mostro == null){
                throw new IllegalArgumentException("Mostro não encontrado");
            }
            // se foi encontrado, então vinculo o mostro às remessas
            for (Long remessaUvaId : data.remessaUvaIdList()) {
                _uvaService.addFkMostro(remessaUvaId, mostro.getId());
                _uvaService.softDelete(remessaUvaId);
            }
            message = "Mostro encontrado e vinculado às remessas";

        } else{
            mostro = _mostroService.register(new DadosCadastroMostro(data.funcionarioId(), 12, null, null));
            // vincular o mostro às remessas
            for (Long remessaUvaId : data.remessaUvaIdList()) {
                _uvaService.addFkMostro(remessaUvaId, mostro.getId());
                _uvaService.softDelete(remessaUvaId);
            }
            // vincular mostro ao deposito
            DepositoMostro depositoMostro = _depositoMostroService.register(new DadosCadastroDepositoMostro(mostro.getId(), data.depositoId(), LocalDate.now(), data.funcionarioId()));

            message = "Mostro criado e vinculado às remessas e ao depósito";
        }

        return new DadosDetalhamentoVinculoDepositoRemessas(data.depositoId(), mostro.getId(), data.funcionarioId(), data.remessaUvaIdList(), message);
    }
}
