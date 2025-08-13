package com.scanai.api.services.implement;

import com.scanai.api.domain.depositopedecuba.Depositopedecuba;
import com.scanai.api.domain.depositopedecuba.dto.DadosCadastroDepositoPeDeCuba;
import com.scanai.api.domain.depositopedecuba.dto.DadosDetalhamentoDepositoPeDeCuba;
import com.scanai.api.domain.pedecuba.Pedecuba;
import com.scanai.api.domain.pedecuba.dto.DadosCadastroPeDeCuba;
import com.scanai.api.domain.pedecuba.dto.DadosDetalhamentoPeDeCuba;
import com.scanai.api.domain.vinculodepositopedecuba.dto.DadosCadastroVinculoDepositoPedecuba;
import com.scanai.api.domain.vinculodepositopedecuba.dto.DadosDetalhamentoVinculoDepositoPedecuba;
import com.scanai.api.services.DepositoPeDeCubaServiceInterface;
import com.scanai.api.services.PeDeCubaServiceInterface;
import com.scanai.api.services.VinculoDepositoPeDeCubaServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

//TODO modificar para um useCase futuramente

@Service
public class VinculoDepositoPeDeCubaService implements VinculoDepositoPeDeCubaServiceInterface {

    @Autowired
    private DepositoPeDeCubaServiceInterface depositoPeDeCubaService;

    @Autowired
    private PeDeCubaServiceInterface peDeCubaService;

    @Transactional
    public DadosDetalhamentoVinculoDepositoPedecuba vincularDepositoPedecuba(DadosCadastroVinculoDepositoPedecuba dados) {
        DadosDetalhamentoPeDeCuba pedecuba = peDeCubaService.register(new DadosCadastroPeDeCuba(dados.funcionarioId(), dados.fkpedecuba(), LocalDate.now(), dados.volume(), dados.produtos()));
        DadosDetalhamentoDepositoPeDeCuba depositopedecuba = depositoPeDeCubaService.register(new DadosCadastroDepositoPeDeCuba(pedecuba.id(), dados.depositoId(), LocalDate.now(), dados.funcionarioId()));
        return new DadosDetalhamentoVinculoDepositoPedecuba(
                depositopedecuba.fkdeposito(),
                depositopedecuba.fkpedecuba(),
                depositopedecuba.fkfuncionario(),
                //TODO verificar o motico dessa message só existir nesse DTO
                "Pe de Cuba criado e vinculado ao deposito com sucesso");
    }
}
