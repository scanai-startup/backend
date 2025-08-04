package com.scanai.api.services.implement;

import com.scanai.api.domain.vinho.DTO.DadosAtualizarVinho;
import com.scanai.api.domain.vinho.DTO.DadosCadastroVinho;
import com.scanai.api.domain.vinho.DTO.DadosDetalhamentoVinho;
import com.scanai.api.domain.vinho.DTO.DadosListagemVinho;
import com.scanai.api.domain.vinho.Vinho;
import com.scanai.api.repositories.VinhoRepository;
import com.scanai.api.services.VinhoServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VinhoService implements VinhoServiceInterface {

    @Autowired
    VinhoRepository vinhoRepository;

    @Transactional
    public Vinho register(DadosCadastroVinho dados){
        return vinhoRepository.save(new Vinho(dados));
    }

    public List<DadosListagemVinho> getAll() {
        return vinhoRepository.findAllByValidTrue().stream().map(DadosListagemVinho::new).toList();
    }

    public Vinho getElement(Long id) {
        return vinhoRepository.getReferenceById(id);
    }

    @Transactional
    public void hardDelete(Long id) {
        vinhoRepository.deleteById(id);
    }

    @Transactional
    public void softDelete(Long id) {
        var vinho = getElement(id);
        vinho.setValid(false);
    }
    @Transactional
    public void activate(Long id) {
        var vinho = getElement(id);
        vinho.setValid(true);
    }

    @Transactional
    public DadosDetalhamentoVinho update(DadosAtualizarVinho dados) {
        Vinho vinho = getElement(dados.id());

        vinho.setDatafimfermentacao(dados.datafimfermentacao());
        vinho.setFkpedecuba(dados.fkpedecuba());
        vinho.setFkmostro(dados.fkmostro());
        vinho.setVolume(dados.volume());
        vinho.setFkpedecuba(dados.fkpedecuba());

        vinhoRepository.save(vinho);

        return new DadosDetalhamentoVinho(vinho);
    }
}
