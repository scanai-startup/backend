package com.scanai.api.services.implement;

import com.scanai.api.domain.enchimento.Enchimento;
import com.scanai.api.domain.enchimento.dto.DadosAtualizarEnchimento;
import com.scanai.api.domain.enchimento.dto.DadosCadastroEnchimento;
import com.scanai.api.domain.enchimento.dto.DadosDetalhamentoEnchimento;
import com.scanai.api.domain.enchimento.dto.DadosListagemEnchimento;
import com.scanai.api.domain.vinho.DTO.DadosAtualizarVinho;
import com.scanai.api.domain.vinho.Vinho;
import com.scanai.api.repositories.EnchimentoRepository;
import com.scanai.api.services.EnchimentoServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EnchimentoService implements EnchimentoServiceInterface {

    @Autowired
    EnchimentoRepository enchimentoRepository;

    @Autowired
    VinhoService vinhoService;

    @Transactional
    public Enchimento register(DadosCadastroEnchimento dados) {
        Vinho vinho = vinhoService.getElement(dados.fkvinho());
        vinho.setVolume(vinho.getVolume() - dados.volumeTrasfega());
        vinho.setDatafimfermentacao(LocalDate.now());
        vinhoService.update(new DadosAtualizarVinho(vinho));

        return enchimentoRepository.save(new Enchimento(dados));
    }

    public List<DadosListagemEnchimento> getAll() {
        return enchimentoRepository.findAll().stream().map(DadosListagemEnchimento::new).toList();
    }

    public Enchimento getElement(Long id) {
        return enchimentoRepository.getReferenceById(id);
    }

    @Transactional
    public void hardDelete(Long id) {
        enchimentoRepository.deleteById(id);
    }

    @Transactional
    public DadosDetalhamentoEnchimento update(DadosAtualizarEnchimento dados) {
        Enchimento enchimento = getElement(dados.id());

        enchimento.setVolume(dados.volumeTrasfega() - dados.volumeChegada());
        enchimento.setDatainiciodespaletizacao(dados.datainiciodespaletizacao());
        enchimento.setDatafimdespaletizacao(dados.datafimdespaletizacao());
        enchimento.setConformeosdespaletizacao(dados.conformeosdespaletizacao());
        enchimento.setAusenciapoeiradespaletizacao(dados.ausenciapoeiradespaletizacao());
        enchimento.setQuantidadegarrafasdespaletizacao(dados.quantidadegarrafasdespaletizacao());
        enchimento.setCoracordodespaletizacao(dados.coracordodespaletizacao());
        enchimento.setDatainicioenxaguadora(dados.datainicioenxaguadora());
        enchimento.setDatafimenxaguadora(dados.datafimenxaguadora());
        enchimento.setFuncionamentoenxaguadora(dados.funcionamentoenxaguadora());
        enchimento.setPressaoentradaenxaguadora(dados.pressaoentradaenxaguadora());
        enchimento.setPressaosaidaenxaguadora(dados.pressaosaidaenxaguadora());
        enchimento.setJatopercorreenxaguadora(dados.jatopercorreenxaguadora());
        enchimento.setBicosfuncionandoenxaguadora(dados.bicosfuncionandoenxaguadora());
        enchimento.setAusenciaaguaenxaguadora(dados.ausenciaaguaenxaguadora());
        enchimento.setDatainicioenchedora(dados.datainicioenchedora());
        enchimento.setDatafimenchedora(dados.datafimenchedora());
        enchimento.setTemperaturaenchedora(dados.temperaturaenchedora());
        enchimento.setNivelmodeloenchedora(dados.nivelmodeloenchedora());
        enchimento.setPressaoenchedora(dados.pressaoenchedora());
        enchimento.setRolhaenchedora(dados.rolhaenchedora());
        enchimento.setQualidaderolhaenchedora(dados.qualidaderolhaenchedora());
        enchimento.setCorposestranhos(dados.corposestranhos());
        enchimento.setFkvinho(dados.fkvinho());
        enchimento.setFkrespproducao(dados.fkrespproducao());
        enchimento.setFkrespdespaletizacao(dados.fkrespdespaletizacao());
        enchimento.setFkrespenchimento(dados.fkrespenchimento());

        return new DadosDetalhamentoEnchimento(enchimento);
    }
}