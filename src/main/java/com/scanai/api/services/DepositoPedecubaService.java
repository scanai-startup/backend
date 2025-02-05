package com.scanai.api.services;

import com.scanai.api.domain.depositomostro.DepositoMostro;
import com.scanai.api.domain.depositomostro.dto.DadosCadastroDepositoMostro;
import com.scanai.api.domain.depositopedecuba.Depositopedecuba;
import com.scanai.api.domain.depositopedecuba.dto.DadosCadastroDepositoPeDeCuba;
import com.scanai.api.domain.depositopedecuba.dto.DadosTrasfegaDepositoPeDeCuba;
import com.scanai.api.domain.mostro.Mostro;
import com.scanai.api.domain.mostro.dto.DadosCadastroMostro;
import com.scanai.api.domain.pedecuba.Pedecuba;
import com.scanai.api.repositories.DepositoPedecubaRepository;
import com.scanai.api.repositories.DepositoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DepositoPedecubaService {

    @Autowired
    DepositoPedecubaRepository depositoPedecubaRepository;

    @Autowired
    DepositoRepository depositoRepository;

    @Autowired
    PedecubaService pedecubaService;

    public Depositopedecuba register(DadosCadastroDepositoPeDeCuba data) {
        Depositopedecuba depositopedecuba = depositoRepository.existsPeDeCubaAtivo(data.fkdeposito());
        if(depositoRepository.existsVinhoAtivo(data.fkdeposito()) != null || depositoRepository.existsMostroAtivo(data.fkdeposito()) != null){
            throw new DataIntegrityViolationException("Impossível inserir, o deposito já contém outro produto ativo");
        }
        var newDepositopedecuba = new Depositopedecuba(data);
        depositoPedecubaRepository.save(newDepositopedecuba);
        return newDepositopedecuba;
    }

    public void setDataFim(Long fkpedecuba){
        // implementando o softDelete
        Depositopedecuba depositopedecuba = depositoPedecubaRepository.findByFkpedecubaAndDatafimIsNull(fkpedecuba);
        depositopedecuba.setDatafim(LocalDate.now());
        depositoPedecubaRepository.save(depositopedecuba);
    }

    public Depositopedecuba trasfegaPedecuba(DadosTrasfegaDepositoPeDeCuba data) {
        Depositopedecuba depositoPedecubaDestino = depositoRepository.existsPeDeCubaAtivo(data.fkdeposito());
        Depositopedecuba depositopedecubaOrigem = depositoPedecubaRepository.findByFkpedecubaAndDatafimIsNull(data.fkpedecuba());
        Pedecuba pedecubaOrigem = pedecubaService.getElement(data.fkpedecuba());
        LocalDate now = LocalDate.now();
        if(depositoRepository.existsVinhoAtivo(data.fkdeposito()) != null || depositoRepository.existsMostroAtivo(data.fkdeposito()) != null){
            throw new DataIntegrityViolationException("Impossível realizar trasfega, o deposito já contém outro produto ativo");
        } else if(depositoPedecubaDestino != null){ //case mistura
            Pedecuba pedecubaDestino = pedecubaService.getElement(depositoPedecubaDestino.getFkpedecuba());
            pedecubaOrigem.setFkpedecuba(pedecubaDestino.getId());
            depositoPedecubaDestino.setDatafim(now);
            pedecubaDestino.setDatafimfermentacao(now);
            depositopedecubaOrigem.setDatafim(now);
            pedecubaOrigem.setVolume(data.volumechegada());
            var newDepositopedecuba = new Depositopedecuba(new DadosCadastroDepositoPeDeCuba(data.fkpedecuba(), data.fkdeposito(), now, data.fkfuncionario()));
            depositoPedecubaRepository.save(newDepositopedecuba);
            return newDepositopedecuba;
        } else {//case vazio
            var newDepositopedecuba = new Depositopedecuba(new DadosCadastroDepositoPeDeCuba(data.fkpedecuba(), data.fkdeposito(), now, data.fkfuncionario()));
            depositopedecubaOrigem.setDatafim(now);
            pedecubaOrigem.setVolume(data.volumechegada());
            depositoPedecubaRepository.save(newDepositopedecuba);
            return newDepositopedecuba;
        }
    }
}
