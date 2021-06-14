package com.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IncorrectCancionException extends RuntimeException {
	public IncorrectCancionException(String message) {
        super(message);
    }
}