package com.scanai.api.domain.employee;

public enum EmployeeRole {

    EMPLOYEE("employee");

    private String role;

    EmployeeRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
