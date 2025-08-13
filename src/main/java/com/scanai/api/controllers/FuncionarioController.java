package com.scanai.api.controllers;

import com.scanai.api.domain.funcionario.dto.ResetPasswordDTO;
import com.scanai.api.repositories.FuncionarioRepository;
import com.scanai.api.services.FuncionarioServiceInterface;
import com.scanai.api.services.implement.FuncionarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioServiceInterface service;

    @Autowired
    private FuncionarioRepository repository;

    @PutMapping("/updatePassword")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void update(@RequestBody @Valid ResetPasswordDTO data){
        service.resetPassword(data);
    }

    @DeleteMapping("/hardDelete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void delete(@PathVariable Long id){
        repository.deleteById(id);
    }
}
