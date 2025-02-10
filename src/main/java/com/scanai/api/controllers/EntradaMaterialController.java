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
@RequestMapping("/entradamaterial")
public class EntradaMaterialController {

    @Autowired
    private EntradaMaterialService service;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<DadosDetalhamentoEntradaMaterial> register(@RequestBody @Valid DadosCadastroEntradaMaterial data, UriComponentsBuilder uriBuilder){
        EntradaMaterial newEntradaMaterial = service.register(data);
        var uri = uriBuilder.path("entradamaterial/register/{id}").buildAndExpand(newEntradaMaterial.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoEntradaMaterial(newEntradaMaterial));
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<DadosListagemEntradaMaterial>> getAll(){
        List<DadosListagemEntradaMaterial> dados = service.getAll();
        return ResponseEntity.ok().body(dados);
    }
}
