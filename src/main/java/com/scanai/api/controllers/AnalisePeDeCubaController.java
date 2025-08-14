package com.scanai.api.controllers;

import com.scanai.api.domain.analisepedecuba.dto.DadosCadastroAnalisePeDeCuba;
import com.scanai.api.domain.analisepedecuba.dto.DadosDetalhamentoAnalisePeDeCuba;
import com.scanai.api.domain.analisepedecuba.dto.DadosListagemAnalisesPeDeCuba;
import com.scanai.api.repositories.AnalisePeDeCubaRepository;
import com.scanai.api.services.AnalisePeDeCubaServiceInterface;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analisepedecuba")
public class AnalisePeDeCubaController {

    @Autowired
    private AnalisePeDeCubaRepository repository;

    @Autowired
    private AnalisePeDeCubaServiceInterface service;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public DadosDetalhamentoAnalisePeDeCuba register(@RequestBody @Valid DadosCadastroAnalisePeDeCuba data) {
        return service.register(data);
    }

    @GetMapping("/listByFk/{fk}")
    public List<DadosListagemAnalisesPeDeCuba> list(@PathVariable Long fk){
        return repository.findAllByFkpedecuba(fk).stream().map(DadosListagemAnalisesPeDeCuba::new).toList();
    }
}
