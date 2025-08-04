package com.scanai.api.services.implement;

import com.scanai.api.domain.uva.Uva;
import com.scanai.api.domain.uva.dto.DadosAtualizarUva;
import com.scanai.api.domain.uva.dto.DadosCadastroUva;
import com.scanai.api.domain.uva.dto.DadosDetalhamentoUva;
import com.scanai.api.domain.uva.dto.DadosListagemUva;
import com.scanai.api.repositories.UvaRepository;
import com.scanai.api.services.UvaServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UvaService implements UvaServiceInterface {

    @Autowired
    UvaRepository uvaRepository;

    @Transactional
    public DadosDetalhamentoUva register(DadosCadastroUva dados){
        return new DadosDetalhamentoUva(uvaRepository.save(new Uva(dados)));
    }

    public DadosDetalhamentoUva getElement(Long id) {
        return new DadosDetalhamentoUva(uvaRepository.getReferenceById(id));
    }

    public List<DadosListagemUva> listAllByValidTrue() {
        return uvaRepository.findAllByValidTrue().stream().map(DadosListagemUva::new).toList();
    }
    public  List<DadosListagemUva> listAll(){
        return uvaRepository.findAll().stream().map(DadosListagemUva::new).toList();
    }

    @Transactional
    public void hardDelete(Long id) {
        uvaRepository.deleteById(id);
    }

    @Transactional
    public DadosDetalhamentoUva update(DadosAtualizarUva dados) {
        Uva uva = uvaRepository.getReferenceById(dados.id());
        uva.update(dados);
        return new DadosDetalhamentoUva(uva);
    }

    @Transactional
    public void softDelete(Long id) {
        Uva uva = uvaRepository.getReferenceById(id);
        uva.setValid(false);
    }

    @Transactional
    public void activate(Long id) {
        Uva uva = uvaRepository.getReferenceById(id);
        uva.setValid(true);
    }

    //TODO verificar motivo disso aqui
    @Transactional
    public void addFkMostro(Long uvaId, Long mostroId){
        Uva uva = uvaRepository.getReferenceById(uvaId);
        uva.setFkmostro(mostroId);
    }
}