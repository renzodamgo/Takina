package com.backend.exceptions;

import com.backend.dtos.ErrorDto;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class NotFoundException extends TakinaException {
	public NotFoundException(String code, String message){
		super(code, HttpStatus.NOT_FOUND.value(), message);
	}

	public NotFoundException(String code, String message, ErrorDto data){
		super(code, HttpStatus.NOT_FOUND.value(), message, Arrays.asList(data));
	}
}
