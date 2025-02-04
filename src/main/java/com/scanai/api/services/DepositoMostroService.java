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
        DepositoMostro depositoMostroExistente = depositoRepository.existsMostroAtivo(data.fkdeposito());
        Mostro mostroOrigem = mostroService.getElement(data.fkmostro());
        if(depositoRepository.existsVinhoAtivo(data.fkdeposito()) != null || depositoRepository.existsPeDeCubaAtivo(data.fkdeposito()) != null){
            throw new DataIntegrityViolationException("Impossível inserir, o deposito já contém outro produto ativo");
        }else if(depositoMostroExistente != null){ // cases de mistura
            Mostro mostroDestino = mostroService.getElement(depositoMostroExistente.getFkmostro());
            if(data.volumetrasfega() == mostroOrigem.getVolume()){//case volume total
                DepositoMostro depositoOrigem = depositoMostroRepository.findByFkmostroAndDatafimIsNull(data.fkmostro());
                depositoOrigem.setDatafim(LocalDate.now());
                DepositoMostro depositoMisturaMostro = mixMostros(data.fkdeposito(), data.fkfuncionario(), mostroOrigem, mostroDestino,
                        depositoMostroExistente, data.volumetrasfega(), data.volumechegada());
                depositoMostroRepository.save(depositoMisturaMostro);
                return depositoMisturaMostro;
            }else if(data.volumetrasfega() < mostroOrigem.getVolume()){//case volume parcial
                float volumeMostroFilho = data.volumechegada() - mostroDestino.getVolume();
                Mostro mostroFilho = mostroService.createMostroFilho(data.fkmostro(), volumeMostroFilho, data.volumetrasfega(), data.fkfuncionario());
                DepositoMostro depositoMisturaMostro = mixMostros(data.fkdeposito(), data.fkfuncionario(), mostroFilho, mostroDestino, depositoMostroExistente,
                        data.volumetrasfega(), data.volumechegada());
                depositoMostroRepository.save(depositoMisturaMostro);
                return depositoMisturaMostro;
            }else {
                throw new DataIntegrityViolationException("Impossível realizar trasfega com volume maior que o existente");
            }
        }else if(data.volumetrasfega() != 0){ // case deposito vazio volume parcial
            float volumeMostroFilho = data.volumechegada();
            Mostro mostroFilho = mostroService.createMostroFilho(data.fkmostro(), volumeMostroFilho, data.volumetrasfega(), data.fkfuncionario());
            var newDepositomostro = new DepositoMostro(new DadosCadastroDepositoMostro(mostroFilho.getId(), data.fkdeposito(), LocalDate.now(), data.fkfuncionario(),
                    data.volumetrasfega(), data.volumechegada()));
            depositoMostroRepository.save(newDepositomostro);
            return newDepositomostro;
        } else{ // case deposito vazio volume total
            DepositoMostro depositoOrigem = depositoMostroRepository.findByFkmostroAndDatafimIsNull(data.fkmostro());
            depositoOrigem.setDatafim(LocalDate.now());
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
    public void setDataFim(Long fkmostro){
        // implementando o softDelete
        DepositoMostro depositoMostro = depositoMostroRepository.findByFkmostroAndDatafimIsNull(fkmostro);
        depositoMostro.setDatafim(LocalDate.now());
        depositoMostroRepository.save(depositoMostro);
    }

    public DepositoMostro mixMostros(Long idDepositoDestino, Long idFuncionario, Mostro mostroOrigem, Mostro mostroDestino, DepositoMostro depositoMostroExistente, float volumeTrasfega, float volumeChegada){
        LocalDate now = LocalDate.now();
        mostroOrigem.setFimfermentacao(now);
        mostroDestino.setFimfermentacao(now);
        mostroOrigem.setValid(false);
        mostroDestino.setValid(false);
        float totalVolume = volumeChegada; //Volume que ficou no tanque de destino após mistura
        Mostro newMostro = mostroService.register(new DadosCadastroMostro(idFuncionario, totalVolume, mostroOrigem.getId(), mostroDestino.getId()));
        depositoMostroExistente.setDatafim(now);
        return new DepositoMostro(new DadosCadastroDepositoMostro(newMostro.getId(), idDepositoDestino, now, idFuncionario, 0,0));
    }
}
