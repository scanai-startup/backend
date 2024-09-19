package com.scanai.api.services;

import com.scanai.api.repositories.FuncionarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

import static org.junit.jupiter.api.Assertions.*;

class FuncionarioServiceTest {

    @Mock
    private FuncionarioRepository repository;

    @Mock //falsificando as dependencias
    private AuthenticationManager authenticationManager;

    @Autowired
    @InjectMocks //para injetar os mocks no lugar das dependencias reais
    private FuncionarioService FuncionarioService;

    @BeforeEach //iniciar antes de tudo o mockito
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should reset password if its ok")
    void resetPasswordCase1() {
    }

}