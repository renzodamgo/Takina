package com.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProyectoNotFoundException extends RuntimeException {
    public ProyectoNotFoundException(String message) {
        super(message);
    }
}