package com.scanai.api.controllers;

import com.scanai.api.domain.higienedeposito.Higienedeposito;
import com.scanai.api.domain.higienedeposito.dto.DadosCadastroHigieneDeposito;
import com.scanai.api.domain.higienedeposito.dto.DadosListagemHigieneDeposito;
import com.scanai.api.repositories.HigienedepositoRepository;
import com.scanai.api.services.HigienedepositoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/higienedeposito")
public class HigienedepositoController {

    @Autowired
    private HigienedepositoService service;

    @Autowired
    private HigienedepositoRepository repository;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity newHigienedeposito(@RequestBody @Valid DadosCadastroHigieneDeposito data, UriComponentsBuilder uriBuilder){
        Higienedeposito newHigienedeposito = service.register(data);
        repository.save(newHigienedeposito);
        var uri = uriBuilder.path("deposito/register/{id}").buildAndExpand(newHigienedeposito.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/listByFk/{fk}")
    public ResponseEntity<List<DadosListagemHigieneDeposito>> listHigienedeposito(@PathVariable Long fk){
        var lista = repository.findAllByFkdeposito(fk).stream().map(DadosListagemHigieneDeposito::new).toList();
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/hardDelete/{id}")
    @Transactional
    public ResponseEntity deleteHigienedeposito(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
