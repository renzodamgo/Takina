package com.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IncorrectProyectoException extends RuntimeException {
	public IncorrectProyectoException(String message) {
        super(message);
    }
}
