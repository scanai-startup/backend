package com.scanai.api.infra.errorsHandlers;

import org.springframework.validation.FieldError;

public record DadosErrors (String field, String message){
    public DadosErrors(FieldError error){
        this(
                error.getField(), error.getDefaultMessage()
        );
    }
}
