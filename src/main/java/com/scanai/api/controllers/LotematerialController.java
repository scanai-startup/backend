package com.scanai.api.controllers;

import com.scanai.api.domain.lotematerial.Lotematerial;
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
    public ResponseEntity regiterLotematerial(@RequestBody @Valid DadosCadastroLoteMaterial data, UriComponentsBuilder uriBuilder){
        Lotematerial newLotematerial = service.register(data);
        repository.save(newLotematerial);
        var uri = uriBuilder.path("lotematerial/register/{id}").buildAndExpand(newLotematerial.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/listByFk/{fk}")
    public ResponseEntity<List<DadosListagemLoteMaterial>> listLotemateriais(@PathVariable Long fk){
        var list = repository.findAllByFkmaterial(fk).stream().map(DadosListagemLoteMaterial::new).toList();
        return ResponseEntity.ok(list);
    }
}
