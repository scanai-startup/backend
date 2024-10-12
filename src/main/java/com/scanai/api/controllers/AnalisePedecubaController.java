package com.scanai.api.controllers;

import com.scanai.api.domain.analisepedecuba.Analisepedecuba;
import com.scanai.api.domain.analisepedecuba.dto.DadosCadastroAnalisePeDeCuba;
import com.scanai.api.domain.analisepedecuba.dto.DadosDetalhamentoAnalisePeDeCuba;
import com.scanai.api.domain.analisepedecuba.dto.DadosListagemAnalisesPeDeCuba;
import com.scanai.api.repositories.AnalisepedecubaRepository;
import com.scanai.api.services.AnalisepedecubaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/analisepedecuba")
public class AnalisePedecubaController {

    @Autowired
    private AnalisepedecubaRepository repository;

    @Autowired
    private AnalisepedecubaService service;

    @PostMapping("/register")
    public ResponseEntity<DadosDetalhamentoAnalisePeDeCuba> regiterAnalisepedecuba(@RequestBody @Valid DadosCadastroAnalisePeDeCuba data, UriComponentsBuilder uriBuilder){
        Analisepedecuba newAnalisepedecuba = service.register(data);
        repository.save(newAnalisepedecuba);
        var uri = uriBuilder.path("analisepedecuba/register/{id}").buildAndExpand(newAnalisepedecuba.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoAnalisePeDeCuba(newAnalisepedecuba));
    }

    @GetMapping("/listByFk/{fk}")
    public ResponseEntity<List<DadosListagemAnalisesPeDeCuba>> listAnalisespedecuba(@PathVariable Long fk){
        var lista = repository.findAllByFkpedecuba(fk).stream().map(DadosListagemAnalisesPeDeCuba::new).toList();
        return ResponseEntity.ok(lista);
    }
}
