package com.scanai.api.controllers;

import com.scanai.api.domain.lotematerial.Lotematerial;
import com.scanai.api.domain.lotematerial.dto.DadosDetalhamentoLoteMaterial;
import com.scanai.api.domain.lotematerial.dto.DadosListagemLoteMaterial;
import com.scanai.api.domain.lotematerial.dto.DadosCadastroLoteMaterial;
import com.scanai.api.repositories.LoteMaterialRepository;
import com.scanai.api.services.LoteMaterialServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loteMaterial")
public class LoteMaterialController {

    @Autowired
    private LoteMaterialRepository repository;

    @Autowired
    private LoteMaterialServiceInterface service;

    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    @PostMapping("/register")
    public DadosDetalhamentoLoteMaterial register(@RequestBody @Valid DadosCadastroLoteMaterial data){
        return service.register(data);
    }

    @GetMapping("/getAll")
    public List<DadosDetalhamentoLoteMaterial> getAll(){
        return service.getAll();
    }
}
