package com.takina.artist.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ArtistaNotFoundException extends RuntimeException {
    public ArtistaNotFoundException(String message) {
        super(message);
    }
}