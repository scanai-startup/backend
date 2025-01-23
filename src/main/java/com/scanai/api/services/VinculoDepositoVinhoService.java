package com.scanai.api.services;

import com.scanai.api.domain.deposito.Deposito;
import com.scanai.api.domain.depositovinho.dto.DadosCadastroDepositoVinho;
import com.scanai.api.domain.mostro.Mostro;
import com.scanai.api.domain.mostrovinho.MostroVinho;
import com.scanai.api.domain.mostrovinho.dto.DadosCadastroMostroVinho;
import com.scanai.api.domain.pedecuba.Pedecuba;
import com.scanai.api.domain.rotulo.Rotulo;
import com.scanai.api.domain.vinculodepositovinho.dto.DadosCadastroVinculoDepositoVinho;
import com.scanai.api.domain.vinculodepositovinho.dto.DadosDetalhamentoVinculoDepositoVinho;
import com.scanai.api.domain.vinho.DTO.DadosCadastroVinho;
import com.scanai.api.domain.vinho.Vinho;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VinculoDepositoVinhoService {
    @Autowired
    private VinhoService _vinhoService;

    @Autowired
    private MostroVinhoService _mostroVinhoService;

    @Autowired
    private MostroService _mostroService;

    @Autowired
    private DepositoService _depositoService;

    @Autowired
    private DepositoVinhoService _depositoVinhoService;

    @Autowired
    private RotuloService _rotuloService;

    @Autowired
    private PedecubaService _pedecubaService;

    @Transactional
    public DadosDetalhamentoVinculoDepositoVinho vincularDepositoVinho(DadosCadastroVinculoDepositoVinho data) {
        // Verificar se o depósito existe e é válido
        Deposito deposito = _depositoService.getElement(data.depositoId());
        if (deposito == null) {
            throw new IllegalArgumentException("Depósito não encontrado");
        }

        // Verificar se o Pé de Cuba existe e é válido
        Pedecuba pedecuba = _pedecubaService.getElement(data.pedecubaId());
        if (pedecuba == null) {
            throw new IllegalArgumentException("Pé de Cuba não encontrado");
        }

        // Somando o volume do pe de cuba ao volume do vinho
        Float volumeVinho = pedecuba.getVolume();

        // Aplicando softdelete no pé de cuba
        pedecuba.setValid(false);

        // Verificar se o rótulo existe e é válido
        Rotulo rotulo = _rotuloService.getElement(data.rotuloId());
        if(rotulo == null){
            throw new IllegalArgumentException("Rótulo não encontrado");
        }

        for (Long mostroId : data.mostroIds()) {
            Mostro mostro = _mostroService.getElement(mostroId);
            if(mostro == null){
                throw new IllegalArgumentException("Mostro não encontrado");
            }
            // Fazendo a somatoria dos volumes dos mostros
            volumeVinho += mostro.getVolume();

        }

        Vinho vinho = _vinhoService.register(new DadosCadastroVinho(data.dataFimFermentacao(), volumeVinho, rotulo.getId(), pedecuba.getId()));

        // Criar registros de vinculo entre os mostros e o vinho
        List<Long> mostroIds = data.mostroIds();
        for (Long mostroId : mostroIds) {
            Mostro mostro = _mostroService.getElement(mostroId);
            if (mostro == null) {
                throw new IllegalArgumentException("Mostro não encontrado");
            }
            // Vinculando cada mostro ao vinho criado anteriormente
            MostroVinho mostroVinho = _mostroVinhoService.register(new DadosCadastroMostroVinho(mostro.getId(), vinho.getId()));
            // aplicando soft delete no mostro
            mostro.setValid(false);
        }

        _depositoVinhoService.register(new DadosCadastroDepositoVinho(vinho.getId(), deposito.getId(), LocalDate.now(), data.funcionarioId()));

        String message = "Vinho " + rotulo.getNome()+" : "+rotulo.getTipo()+" criado e vinculado com sucesso";
        // Retornar os detalhes do vínculo criado
        return new DadosDetalhamentoVinculoDepositoVinho(
                vinho.getId(),
                vinho.getVolume(),
                deposito.getId(),
                pedecuba.getId(),
                rotulo.getId(),
                mostroIds,
                message
        );
    }
}
