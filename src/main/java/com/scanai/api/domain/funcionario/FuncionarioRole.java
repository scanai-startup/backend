package com.scanai.api.domain.funcionario;

public enum FuncionarioRole {

    FUNCIONARIO("funcionario");

    private String role;

    FuncionarioRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
