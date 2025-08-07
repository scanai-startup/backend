package com.scanai.api.controllers;

import com.scanai.api.domain.analiseprefermentacao.dto.DadosDetalhamentoAnalisePreFermentacao;
import com.scanai.api.domain.funcionario.dto.AuthenticationDTO;
import com.scanai.api.domain.funcionario.Funcionario;
import com.scanai.api.domain.funcionario.dto.DadosDetalhamentoFuncionario;
import com.scanai.api.domain.funcionario.dto.LoginResponseDTO;
import com.scanai.api.domain.funcionario.dto.RegisterDTO;
import com.scanai.api.infra.security.TokenService;
import com.scanai.api.repositories.FuncionarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public LoginResponseDTO login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.matricula(), data.senha());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Funcionario) auth.getPrincipal());
        return new LoginResponseDTO(token);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    @Transactional
    public DadosDetalhamentoFuncionario register(@RequestBody @Valid RegisterDTO data){
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        Funcionario newFuncionario = new Funcionario(data.matricula(), encryptedPassword, data.role(), data.nome(), data.email());
        this.repository.save(newFuncionario);
        return new DadosDetalhamentoFuncionario(newFuncionario);
    }
}
