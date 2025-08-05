package com.scanai.api.controllers;

import com.scanai.api.domain.analisepedecuba.Analisepedecuba;
import com.scanai.api.domain.analisepedecuba.dto.DadosCadastroAnalisePeDeCuba;
import com.scanai.api.domain.analisepedecuba.dto.DadosDetalhamentoAnalisePeDeCuba;
import com.scanai.api.domain.analisepedecuba.dto.DadosListagemAnalisesPeDeCuba;
import com.scanai.api.repositories.AnalisepedecubaRepository;
import com.scanai.api.services.AnalisePeDeCubaServiceInterface;
import com.scanai.api.services.implement.AnalisePeDeCubaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/analisepedecuba")
public class AnalisePeDeCubaController {

    @Autowired
    private AnalisepedecubaRepository repository;

    @Autowired
    private AnalisePeDeCubaServiceInterface service;

    @PostMapping("/register")
    public DadosDetalhamentoAnalisePeDeCuba register(@RequestBody @Valid DadosCadastroAnalisePeDeCuba data){
        Analisepedecuba newAnalisepedecuba = service.register(data);
        return new DadosDetalhamentoAnalisePeDeCuba(newAnalisepedecuba);
    }

    @GetMapping("/listByFk/{fk}")
    public List<DadosListagemAnalisesPeDeCuba> list(@PathVariable Long fk){
        return repository.findAllByFkpedecuba(fk).stream().map(DadosListagemAnalisesPeDeCuba::new).toList();
    }
}
