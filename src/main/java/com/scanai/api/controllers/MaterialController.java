package com.scanai.api.controllers;

import com.scanai.api.domain.material.Material;
import com.scanai.api.domain.material.dto.DadosCadastroMaterial;
import com.scanai.api.domain.material.dto.DadosDetalhamentoMaterial;
import com.scanai.api.domain.material.dto.DadosListagemMaterial;
import com.scanai.api.repositories.MaterialRepository;
import com.scanai.api.services.MaterialServiceInterface;
import com.scanai.api.services.implement.MaterialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/material")
public class MaterialController {
    @Autowired
    private MaterialServiceInterface service;

    @PostMapping("/register")
    public DadosDetalhamentoMaterial register(@RequestBody @Valid DadosCadastroMaterial data){
        return service.register(data);
    }

    @GetMapping("/getAll")
    public List<DadosListagemMaterial> list(){
        return service.getAll();
    }


}
