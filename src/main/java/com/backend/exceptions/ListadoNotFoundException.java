package com.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ListadoNotFoundException extends RuntimeException {
    public ListadoNotFoundException(String message) {
        super(message);
    }
}
