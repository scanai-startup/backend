package com.scanai.api.services.implement;

import com.scanai.api.domain.liberacao.Liberacao;
import com.scanai.api.domain.liberacao.dto.DadosAtualizarLiberacao;
import com.scanai.api.domain.liberacao.dto.DadosCadastroLiberacao;
import com.scanai.api.domain.liberacao.dto.DadosDetalhamentoLiberacao;
import com.scanai.api.domain.liberacao.dto.DadosListagemLiberacao;
import com.scanai.api.repositories.LiberacaoRepository;
import com.scanai.api.services.LiberacaoServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiberacaoService implements LiberacaoServiceInterface {

    @Autowired
    LiberacaoRepository liberacaoRepository;

    @Transactional
    public Liberacao register(DadosCadastroLiberacao dados){
        return liberacaoRepository.save(new Liberacao(dados));
    }

    public List<DadosListagemLiberacao> getAll() {
        return liberacaoRepository.findAll().stream().map(DadosListagemLiberacao::new).toList();
    }

    public Liberacao getElement(Long id) {
        return liberacaoRepository.getReferenceById(id);
    }

    @Transactional
    public void hardDelete(Long id) {
        liberacaoRepository.deleteById(id);
    }

    @Transactional
    public DadosDetalhamentoLiberacao update(DadosAtualizarLiberacao dados) {
        Liberacao liberacao = getElement(dados.id());

        liberacao.setDatafim(dados.datafim());
        liberacao.setFkfuncionario(dados.fkfuncionario());
        liberacao.setGfs(dados.gfs());
        liberacao.setFkrotulagem(dados.fkrotulagem());
        liberacao.setDatainicio(dados.datainicio());

        return new DadosDetalhamentoLiberacao(liberacao);
    }
}
