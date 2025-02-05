package com.scanai.api.services;

import com.scanai.api.domain.depositovinho.Depositovinho;
import com.scanai.api.domain.depositovinho.dto.DadosCadastroDepositoVinho;
import com.scanai.api.domain.depositovinho.dto.DadosTrasfegaDepositoVinho;
import com.scanai.api.domain.vinho.Vinho;
import com.scanai.api.repositories.DepositoRepository;
import com.scanai.api.repositories.DepositoVinhoRepository;
import com.scanai.api.repositories.VinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DepositoVinhoService {

    @Autowired
    DepositoVinhoRepository depositoVinhoRepository;

    @Autowired
    DepositoRepository depositoRepository;

    @Autowired
    VinhoRepository vinhoRepository;

    public Depositovinho register(DadosCadastroDepositoVinho data) {
        if(depositoRepository.existsVinhoAtivo(data.fkdeposito()) != null || depositoRepository.existsPeDeCubaAtivo(data.fkdeposito()) != null || depositoRepository.existsMostroAtivo(data.fkdeposito()) != null){
            throw new DataIntegrityViolationException("Impossível inserir, o deposito já contém outro produto ativo");
        }
        var newDepositovinho = new Depositovinho(data);
        depositoVinhoRepository.save(newDepositovinho);
        return newDepositovinho;
    }

    public Depositovinho trasfegaVinho(DadosTrasfegaDepositoVinho data) {
        if(depositoRepository.existsVinhoAtivo(data.fkdeposito()) != null || depositoRepository.existsPeDeCubaAtivo(data.fkdeposito()) != null || depositoRepository.existsMostroAtivo(data.fkdeposito()) != null){
            throw new DataIntegrityViolationException("Impossível inserir, o deposito já contém outro produto ativo");
        }
        //case unico, volume total por enquanto
        Depositovinho depositoVinhoOrigem = depositoVinhoRepository.findByFkvinhoAndDatafimIsNull(data.fkvinho());
        Vinho vinhoOrigem = vinhoRepository.getReferenceById(data.fkvinho());
        depositoVinhoOrigem.setDatafim(LocalDate.now()); //finaliza fermentação
        vinhoOrigem.setVolume(data.volumechegada()); //atualiza com perdas
        var newDepositovinho = new Depositovinho(new DadosCadastroDepositoVinho(data.fkvinho(), data.fkdeposito(), LocalDate.now(), data.fkfuncionario()));
        depositoVinhoRepository.save(newDepositovinho);
        return newDepositovinho;
    }

    public void setDataFim(Long fkvinho){
        // implementando o softDelete
        Depositovinho depositovinho = depositoVinhoRepository.findByFkvinhoAndDatafimIsNull(fkvinho);
        depositovinho.setDatafim(LocalDate.now());
        depositoVinhoRepository.save(depositovinho);
    }

}
