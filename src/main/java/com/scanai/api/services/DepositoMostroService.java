package com.scanai.api.services;

import com.scanai.api.domain.depositomostro.DepositoMostro;
import com.scanai.api.domain.depositomostro.dto.DadosCadastroDepositoMostro;
import com.scanai.api.domain.mostro.Mostro;
import com.scanai.api.domain.mostro.dto.DadosCadastroMostro;
import com.scanai.api.repositories.DepositoMostroRepository;
import com.scanai.api.repositories.DepositoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DepositoMostroService {

    @Autowired
    DepositoMostroRepository depositoMostroRepository;

    @Autowired
    DepositoRepository depositoRepository;

    @Autowired
    MostroService mostroService;


    public DepositoMostro register(DadosCadastroDepositoMostro data) {
        DepositoMostro depositoMostro = depositoRepository.existsMostroAtivo(data.fkdeposito());
        LocalDate now = LocalDate.now();
        if(depositoRepository.existsVinhoAtivo(data.fkdeposito()) != null || depositoRepository.existsPeDeCubaAtivo(data.fkdeposito()) != null){
            throw new DataIntegrityViolationException("Impossível inserir, o deposito já contém outro produto ativo");
        }else if(depositoMostro != null){
            int totalVolume = 0;
            Mostro mostro1 = mostroService.getElement(data.fkmostro());
            Mostro mostro2 = mostroService.getElement(depositoMostro.getFkmostro());
            mostro1.setFimfermentacao(now);
            mostro2.setFimfermentacao(now);
            totalVolume = mostro1.getVolume() + mostro2.getVolume();
            Mostro newMostro = mostroService.register(new DadosCadastroMostro(data.fkfuncionario(), totalVolume, mostro1.getId(), mostro2.getId()));
            depositoMostro.setDatafim(now);
            var newDepositomostro = new DepositoMostro(new DadosCadastroDepositoMostro(newMostro.getId(), data.fkdeposito(), now, data.fkfuncionario()));
            depositoMostroRepository.save(newDepositomostro);
            return newDepositomostro;
        }else{
            var newDepositomostro = new DepositoMostro(data);
            depositoMostroRepository.save(newDepositomostro);
            return newDepositomostro;
        }
    }

    public DepositoMostro findDepositoMostroByFkdepositoFkmostro(Long fkdeposito, Long fkmostro){
        return depositoMostroRepository.findByFkdepositoAndFkmostro(fkdeposito, fkmostro);
    }
    public void softDelete(Long fkdeposito, Long fkmostro){
        // implementando o softDelete
        DepositoMostro depositoMostro = depositoMostroRepository.findByFkdepositoAndFkmostro(fkdeposito, fkmostro);
        depositoMostro.setDatafim(LocalDate.now());
        depositoMostroRepository.save(depositoMostro);
    }
}
