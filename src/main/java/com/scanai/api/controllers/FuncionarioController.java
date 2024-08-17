package com.scanai.api.controllers;

import com.scanai.api.domain.funcionario.dto.ResetPasswordDTO;
import com.scanai.api.repositories.FuncionarioRepository;
import com.scanai.api.services.FuncionarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    FuncionarioService service;

    @Autowired
    FuncionarioRepository repository;

    @PutMapping("/updatePassword")
    @Transactional
    public ResponseEntity updatePassword(@RequestBody @Valid ResetPasswordDTO data){
        service.resetPassword(data);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
