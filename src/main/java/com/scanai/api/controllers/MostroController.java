package com.scanai.api.controllers;

import com.scanai.api.domain.deposito.dto.DadosListagemDeposito;
import com.scanai.api.domain.mostro.Mostro;
import com.scanai.api.domain.mostro.dto.DadosCadastroMostro;
import com.scanai.api.domain.mostro.dto.DadosDetalhamentoMostro;
import com.scanai.api.domain.mostro.dto.DadosListagemMostro;
import com.scanai.api.repositories.MostroRepository;
import com.scanai.api.services.MostroService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/mostro")
public class MostroController {

    @Autowired
    private MostroRepository repository;

    @Autowired
    private MostroService service;

    @PostMapping("/register")
    public ResponseEntity<DadosDetalhamentoMostro> register(@RequestBody @Valid DadosCadastroMostro data, UriComponentsBuilder uriBuilder){
        Mostro newMostro = service.register(data);
        var uri = uriBuilder.path("mostro/register/{id}").buildAndExpand(newMostro.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMostro(newMostro));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DadosListagemMostro>> getAll(){
        return ResponseEntity.ok().body(repository.findAllByValidTrue().stream().map(DadosListagemMostro::new).toList());
    }

    @PutMapping("/softDelete/{id}")
    @Transactional
    public ResponseEntity<?> softDelete(@PathVariable Long id){
        service.softDelete(repository.getReferenceById(id));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/activate/{id}")
    @Transactional
    public ResponseEntity<?> activate(@PathVariable Long id){
        service.softDelete(repository.getReferenceById(id));
        return ResponseEntity.ok().build();
    }

}

