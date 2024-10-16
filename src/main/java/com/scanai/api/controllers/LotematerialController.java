package com.scanai.api.controllers;

import com.scanai.api.domain.lotematerial.Lotematerial;
import com.scanai.api.domain.lotematerial.dto.DadosDetalhamentoLoteMaterial;
import com.scanai.api.domain.lotematerial.dto.DadosListagemLoteMaterial;
import com.scanai.api.domain.lotematerial.dto.DadosCadastroLoteMaterial;
import com.scanai.api.repositories.LotematerialRepository;
import com.scanai.api.services.LotematerialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/lotematerial")
public class LotematerialController {

    @Autowired
    private LotematerialRepository repository;

    @Autowired
    private LotematerialService service;

    @PostMapping("/register")
    public ResponseEntity<DadosDetalhamentoLoteMaterial> register(@RequestBody @Valid DadosCadastroLoteMaterial data, UriComponentsBuilder uriBuilder){
        Lotematerial newLotematerial = service.register(data);
        var uri = uriBuilder.path("lotematerial/register/{id}").buildAndExpand(newLotematerial.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoLoteMaterial(newLotematerial));
    }

    @GetMapping("/listByFk/{fk}")
    public ResponseEntity<List<DadosListagemLoteMaterial>> list(@PathVariable Long fk){
        var list = repository.findAllByFkmaterial(fk).stream().map(DadosListagemLoteMaterial::new).toList();
        return ResponseEntity.ok(list);
    }
}
