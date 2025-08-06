package com.scanai.api.services.implement;

import com.scanai.api.domain.pedecuba.Pedecuba;
import com.scanai.api.domain.pedecuba.dto.DadosCadastroPeDeCuba;
import com.scanai.api.domain.pedecuba.dto.DadosDetalhamentoPeDeCuba;
import com.scanai.api.domain.produtoadcpedecuba.dto.DadosCadastroProdutoAdicionadoPeDeCuba;
import com.scanai.api.repositories.PedecubaRepository;
import com.scanai.api.services.PeDeCubaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PeDeCubaService implements PeDeCubaServiceInterface {

    @Autowired
    PedecubaRepository peDeCubaRepository;

    @Autowired
    ProdutoAdicionadoPeDeCubaService produtoAdicionadoPeDeCubaService;

    public Pedecuba register(DadosCadastroPeDeCuba dados) {
        Pedecuba newPedecuba = new Pedecuba(dados);
        peDeCubaRepository.save(newPedecuba);
        if(dados.produtos() != null){
            produtoAdicionadoPeDeCubaService.register(new DadosCadastroProdutoAdicionadoPeDeCuba(newPedecuba.getId(), dados.produtos()));
        }
        return newPedecuba;
    }

    public void softDelete(Long id) {
        Pedecuba peDeCuba = peDeCubaRepository.getReferenceById(id);
        peDeCuba.setValid(false);
    }

    public void activate(Long id) {
        Pedecuba peDeCuba = peDeCubaRepository.getReferenceById(id);
        peDeCuba.setValid(true);
    }

    public Pedecuba getElement(Long id) {
        return peDeCubaRepository.getReferenceById(id);
    }

    public List<DadosDetalhamentoPeDeCuba> getAll(){
        return peDeCubaRepository.findAllByValidTrue();
    }
}
