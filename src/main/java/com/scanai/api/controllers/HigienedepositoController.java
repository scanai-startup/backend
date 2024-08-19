package com.scanai.api.controllers;

import com.scanai.api.domain.higienedeposito.Higienedeposito;
import com.scanai.api.domain.higienedeposito.dto.ReqListHigienedepositoDTO;
import com.scanai.api.domain.higienedeposito.dto.RegisterHigienedepositoDTO;
import com.scanai.api.domain.higienedeposito.dto.ResListHigienedeposito;
import com.scanai.api.domain.uva.dto.DadosListagemUva;
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
    public ResponseEntity newHigienedeposito(@RequestBody @Valid RegisterHigienedepositoDTO data, UriComponentsBuilder uriBuilder){
        Higienedeposito newHigienedeposito = service.createHigienedeposito(data);
        repository.save(newHigienedeposito);
        var uri = uriBuilder.path("deposito/register/{id}").buildAndExpand(newHigienedeposito.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/listHigienedeposito")
    public ResponseEntity<List<ResListHigienedeposito>> listHigienedeposito(@RequestBody @Valid ReqListHigienedepositoDTO data){
        var lista = repository.findAllByFkdeposito(data.fkdeposito()).stream().map(ResListHigienedeposito::new).toList();
        return ResponseEntity.ok(lista);
    }
}
