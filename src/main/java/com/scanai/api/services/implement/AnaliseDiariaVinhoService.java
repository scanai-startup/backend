package com.scanai.api.services.implement;

import com.scanai.api.domain.analisediariavinho.AnaliseDiariaVinho;
import com.scanai.api.domain.analisediariavinho.dto.DadosAtualizarAnaliseDiariaVinho;
import com.scanai.api.domain.analisediariavinho.dto.DadosCadastroAnaliseDiariaVinho;
import com.scanai.api.domain.analisediariavinho.dto.DadosDetalhamentoAnaliseDiariaVinho;
import com.scanai.api.domain.analisediariavinho.dto.DadosListagemAnaliseDiariaVinho;
import com.scanai.api.repositories.AnaliseDiariaVinhoRepository;
import com.scanai.api.services.AnaliseDiariaVinhoServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnaliseDiariaVinhoService implements AnaliseDiariaVinhoServiceInterface {

    @Autowired
    AnaliseDiariaVinhoRepository analiseDiariaVinhoRepository;

    @Transactional
    public DadosDetalhamentoAnaliseDiariaVinho register(DadosCadastroAnaliseDiariaVinho dados){
        return new DadosDetalhamentoAnaliseDiariaVinho(analiseDiariaVinhoRepository.save(new AnaliseDiariaVinho(dados)));
    }

    public List<DadosListagemAnaliseDiariaVinho> getAll() {
        return analiseDiariaVinhoRepository.findAll().stream().map(DadosListagemAnaliseDiariaVinho::new).toList();
    }

    public DadosDetalhamentoAnaliseDiariaVinho getElement(Long id) {
        return new DadosDetalhamentoAnaliseDiariaVinho(analiseDiariaVinhoRepository.getReferenceById(id));
    }

    @Transactional
    public void hardDelete(Long id) {
        analiseDiariaVinhoRepository.deleteById(id);
    }

    @Transactional
    public DadosDetalhamentoAnaliseDiariaVinho update(DadosAtualizarAnaliseDiariaVinho dados) {
        AnaliseDiariaVinho analiseDiariavinho = analiseDiariaVinhoRepository.getReferenceById(dados.id());

        analiseDiariavinho.setFkvinho(dados.fkvinho());
        analiseDiariavinho.setFkfuncionario(dados.fkfuncionario());
        analiseDiariavinho.setDensidade(dados.densidade());
        analiseDiariavinho.setData(LocalDateTime.now());
        analiseDiariavinho.setTemperatura(dados.temperatura());
        analiseDiariavinho.setPressao(dados.pressao());

        return new DadosDetalhamentoAnaliseDiariaVinho(analiseDiariavinho);
    }
}
