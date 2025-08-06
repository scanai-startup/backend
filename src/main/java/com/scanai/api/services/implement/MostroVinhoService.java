package com.scanai.api.services.implement;

import com.scanai.api.domain.mostro.Mostro;
import com.scanai.api.domain.mostrovinho.MostroVinho;
import com.scanai.api.domain.mostrovinho.dto.DadosAtualizarMostroVinho;
import com.scanai.api.domain.mostrovinho.dto.DadosCadastroMostroVinho;
import com.scanai.api.domain.mostrovinho.dto.DadosDetalhamentoMostroVinho;
import com.scanai.api.domain.mostrovinho.dto.DadosListagemMostroVinho;
import com.scanai.api.repositories.MostroVinhoRepository;
import com.scanai.api.services.MostroVinhoServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MostroVinhoService implements MostroVinhoServiceInterface {

    @Autowired
    MostroVinhoRepository mostroVinhoRepository;

    @Transactional
    public MostroVinho register(DadosCadastroMostroVinho dados){
        return mostroVinhoRepository.save(new MostroVinho(dados));
    }
    public MostroVinho getElement(Long id) {

        return mostroVinhoRepository.getReferenceById(id);
    }

    public List<DadosListagemMostroVinho> getAll() {
        return mostroVinhoRepository.findAll().stream().map(DadosListagemMostroVinho::new).toList();
    }

    @Transactional
    public void hardDelete(Long id) {
        mostroVinhoRepository.deleteById(id);
    }

    @Transactional
    public DadosDetalhamentoMostroVinho update(DadosAtualizarMostroVinho dados) {
        MostroVinho mostroVinho = getElement(dados.id());

        mostroVinho.setFkmostro(dados.fkmostro());
        mostroVinho.setFkvinho(dados.fkvinho());
        mostroVinhoRepository.save(mostroVinho);

        return new DadosDetalhamentoMostroVinho(mostroVinho);
    }
}
