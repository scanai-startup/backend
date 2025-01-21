package com.scanai.api.services;

import com.scanai.api.domain.depositopedecuba.Depositopedecuba;
import com.scanai.api.domain.depositopedecuba.dto.DadosCadastroDepositoPeDeCuba;
import com.scanai.api.domain.pedecuba.Pedecuba;
import com.scanai.api.domain.pedecuba.dto.DadosCadastroPeDeCuba;
import com.scanai.api.domain.vinculodepositopedecuba.dto.DadosCadastroVinculoDepositoPedecuba;
import com.scanai.api.domain.vinculodepositopedecuba.dto.DadosDetalhamentoVinculoDepositoPedecuba;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class VinculoDepositoPedecubaService {
    @Autowired
    private DepositoPedecubaService _depositoPedecubaService;

    @Autowired
    private PedecubaService _pedecubaService;

    @Transactional
    public DadosDetalhamentoVinculoDepositoPedecuba vincularDepositoPedecuba(DadosCadastroVinculoDepositoPedecuba dados) {
        Pedecuba pedecuba = _pedecubaService.register(new DadosCadastroPeDeCuba(dados.funcionarioId(), dados.fkpedecuba(), LocalDate.now(), dados.volume(), dados.produtos()));
        Depositopedecuba depositopedecuba = _depositoPedecubaService.register(new DadosCadastroDepositoPeDeCuba(pedecuba.getId(), dados.depositoId(), LocalDate.now(), dados.funcionarioId()));
        return new DadosDetalhamentoVinculoDepositoPedecuba(
                depositopedecuba.getFkdeposito(),
                depositopedecuba.getFkpedecuba(),
                depositopedecuba.getFkfuncionario(),
                "Pe de Cuba criado e vinculado ao deposito com sucesso");
    }
}
