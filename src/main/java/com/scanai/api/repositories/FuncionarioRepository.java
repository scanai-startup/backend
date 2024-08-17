package com.scanai.api.repositories;

import com.scanai.api.domain.funcionario.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    UserDetails findByMatricula(String matricula);

}
