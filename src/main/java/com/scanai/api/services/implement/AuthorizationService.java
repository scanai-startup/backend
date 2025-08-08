package com.scanai.api.services.implement;

import com.scanai.api.domain.funcionario.Funcionario;
import com.scanai.api.repositories.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public UserDetails loadUserByUsername(String matricula) {
        Optional<UserDetails> user = funcionarioRepository.findByMatricula(matricula);
        if(user.isEmpty()){
            throw new EntityNotFoundException("Funcionario not found");
        }
        return user.get();
    }
}
