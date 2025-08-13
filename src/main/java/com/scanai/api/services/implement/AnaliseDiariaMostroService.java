package com.scanai.api.services.implement;

import com.scanai.api.domain.analisediariamostro.AnaliseDiariaMostro;
import com.scanai.api.domain.analisediariamostro.dto.DadosAtualizarAnaliseDiariaMostro;
import com.scanai.api.domain.analisediariamostro.dto.DadosCadastroAnaliseDiariaMostro;
import com.scanai.api.domain.analisediariamostro.dto.DadosDetalhamentoAnaliseDiariaMostro;
import com.scanai.api.domain.analisediariamostro.dto.DadosListagemAnaliseDiariaMostro;
import com.scanai.api.repositories.AnaliseDiariaMostroRepository;
import com.scanai.api.services.AnaliseDiariaMostroServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnaliseDiariaMostroService implements AnaliseDiariaMostroServiceInterface {

    @Autowired
    AnaliseDiariaMostroRepository analiseMostroRepository;

    @Transactional
    public DadosDetalhamentoAnaliseDiariaMostro register(DadosCadastroAnaliseDiariaMostro dados){
        return new DadosDetalhamentoAnaliseDiariaMostro(analiseMostroRepository.save(new AnaliseDiariaMostro(dados)));
    }

    public List<DadosListagemAnaliseDiariaMostro> getAll() {
        return analiseMostroRepository.findAll().stream().map(DadosListagemAnaliseDiariaMostro::new).toList();
    }

    public DadosDetalhamentoAnaliseDiariaMostro getElement(Long id) {
        return new DadosDetalhamentoAnaliseDiariaMostro(analiseMostroRepository.getReferenceById(id));
    }

    @Transactional
    public void hardDelete(Long id) {
        analiseMostroRepository.deleteById(id);
    }

    @Transactional
    public DadosDetalhamentoAnaliseDiariaMostro update(DadosAtualizarAnaliseDiariaMostro dados) {
        AnaliseDiariaMostro analiseDiariamostro = analiseMostroRepository.getReferenceById(dados.id());
        analiseDiariamostro.setData(dados.data());
        analiseDiariamostro.setDensidade(dados.densidade());
        analiseDiariamostro.setFkfuncionario(dados.fkfuncionario());
        analiseDiariamostro.setFkmostro(dados.fkmostro());
        analiseDiariamostro.setTemperatura(dados.temperatura());
        return new DadosDetalhamentoAnaliseDiariaMostro(analiseDiariamostro);
    }
}
