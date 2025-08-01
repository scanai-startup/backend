package com.scanai.api.controllers;

import com.scanai.api.domain.entradamaterial.EntradaMaterial;
import com.scanai.api.domain.entradamaterial.dto.DadosCadastroEntradaMaterial;
import com.scanai.api.domain.entradamaterial.dto.DadosDetalhamentoEntradaMaterial;
import com.scanai.api.domain.entradamaterial.dto.DadosListagemEntradaMaterial;
import com.scanai.api.services.EntradaMaterialService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/entradaMaterial")
public class EntradaMaterialController {

    @Autowired
    private EntradaMaterialService service;

    @PostMapping("/register")
    @Transactional
    public DadosDetalhamentoEntradaMaterial register(@RequestBody @Valid DadosCadastroEntradaMaterial data){
        EntradaMaterial newEntradaMaterial = service.register(data);
        return new DadosDetalhamentoEntradaMaterial(newEntradaMaterial);
    }

    @GetMapping("/getAll")
    public List<DadosListagemEntradaMaterial> getAll(){
        return service.getAll();
    }
}
