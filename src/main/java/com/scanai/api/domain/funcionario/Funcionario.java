package com.scanai.api.domain.funcionario;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "tb_funcionario")
@Entity(name = "tb_funcionario")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Funcionario implements UserDetails {

    @Id
    private long id;

    private String nome;
    private String matricula; //login
    private String email;
    private String cpf; //initial password
    private String senha;

    @Enumerated(EnumType.STRING)
    private FuncionarioRole role;

    public Funcionario(String matricula, String encryptedPassword, FuncionarioRole role, String nome, String email) {
        this.role = role;
        this.matricula = matricula;
        this.cpf = encryptedPassword;
        this.senha = encryptedPassword;
        this.nome = nome;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == FuncionarioRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_FUNCIONARIO"));
        }
        else {
            return List.of(new SimpleGrantedAuthority("ROLE_FUNCIONARIO"));
        }
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.matricula;
    }

    public Long getId(){
        return this.id;
    }

}
