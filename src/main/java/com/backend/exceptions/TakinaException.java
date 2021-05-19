package com.backend.exceptions;

import com.backend.dtos.ErrorDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TakinaException extends Exception{
	private final String code;
	private final int responseCode;
	private final List<ErrorDto> errorList=new ArrayList<>();

	public TakinaException(String code,int responseCode,String message){
		super(message);
		this.code=code;
		this.responseCode=responseCode;
	}

	public TakinaException(String code,int responseCode,String message,
							List<ErrorDto> errorList){
		super(message);
		this.code=code;
		this.responseCode=responseCode;
		this.errorList.addAll(errorList);
	}
}
