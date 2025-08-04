package com.scanai.api.infra.exceptions.customExceptions;

import org.apache.coyote.BadRequestException;

public class BadRequest extends RuntimeException {
    public BadRequest(String message){
        super(message);
    }
}
