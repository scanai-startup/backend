package com.scanai.api.services;

import com.scanai.api.domain.deposito.Deposito;
import com.scanai.api.domain.deposito.dto.DadosCadastroDeposito;
import com.scanai.api.domain.deposito.dto.DadosAtualizarDeposito;
import com.scanai.api.domain.deposito.dto.DadosInformacoesDepositos;
import com.scanai.api.domain.deposito.dto.DadosTrasfega;
import com.scanai.api.domain.depositomostro.dto.DadosCadastroDepositoMostro;
import com.scanai.api.repositories.DepositoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DepositoService {

    @Autowired
    private DepositoRepository repository;

    @Autowired
    private DepositoMostroService depositoMostroService;

    public Deposito register(DadosCadastroDeposito data){
        Deposito newDeposito = new Deposito(data);
        repository.save(newDeposito);
        return newDeposito;
    }

    public Deposito update(DadosAtualizarDeposito data){
        Deposito deposito = repository.findByNumerodeposito(data.numeroAtual());
        if(deposito == null){
            throw new EntityNotFoundException("Deposito: " + data.numeroAtual() + " Não Encontrado");
        }
        deposito.setNumerodeposito(data.numeroNovo());
        return deposito;
    }

    public void softDelete(Deposito deposito) {
        deposito.setValid(false);
    }

    public void activate(Deposito deposito) {
        deposito.setValid(true);
    }

    public List<Deposito> getAll(){
        return repository.findAllByValidTrue();
    }

    public Deposito getElement(Long id){
        return repository.findDepositoById(id);
    }

    public List<DadosInformacoesDepositos> getAllDepositosWithInformations(){
        return repository.getAllDepositosWithInformations();
    }


    public DadosInformacoesDepositos getDepositoWithIdWithInformations(Long id) {
        return repository.getDepositoWithIdWithInformations(id);
    }

    public DadosTrasfega RealizarTrasfega(Long origemId, Long destinoId){
        DadosInformacoesDepositos dadosInformacoesDepositos = getDepositoWithIdWithInformations(origemId);
        if(dadosInformacoesDepositos == null){
            throw new EntityNotFoundException("Deposito: " + origemId + " Não Encontrado");
        }
        if(dadosInformacoesDepositos.getConteudo() == "Mostro"){
            // Apaga logicamente o mostro do deposito de origem e cria um novo deposito mostro no deposito de destino
            depositoMostroService.softDelete(origemId, dadosInformacoesDepositos.getIdConteudo());
            DadosCadastroDepositoMostro dadosCadastroDepositoMostro = new DadosCadastroDepositoMostro(dadosInformacoesDepositos.getIdConteudo(), destinoId, LocalDate.now(), 1L);
            depositoMostroService.register(dadosCadastroDepositoMostro);

            return new DadosTrasfega(origemId, destinoId, dadosInformacoesDepositos.getIdConteudo(), dadosInformacoesDepositos.getConteudo());
        } else if(dadosInformacoesDepositos.getConteudo() == "Pe de cuba"){

        } else if (dadosInformacoesDepositos.getConteudo() == "Vinho"){

        } else {
            throw new IllegalArgumentException("Deposito: " + origemId + " Não possui conteudo para trasfega");

        }
        return null;
    }
}
