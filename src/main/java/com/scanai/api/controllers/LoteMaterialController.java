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
@RequestMapping("/loteMaterial")
public class LoteMaterialController {

    @Autowired
    private LotematerialRepository repository;

    @Autowired
    private LotematerialService service;

    @PostMapping("/register")
    public DadosDetalhamentoLoteMaterial register(@RequestBody @Valid DadosCadastroLoteMaterial data){
        Lotematerial newLotematerial = service.register(data);
        return new DadosDetalhamentoLoteMaterial(newLotematerial);
    }

    @GetMapping("/getAll")
    public List<DadosListagemLoteMaterial> getAll(){
        List<Lotematerial> lotematerialList = service.getAll();
        return lotematerialList.stream().map(DadosListagemLoteMaterial::new).toList();
    }
}
