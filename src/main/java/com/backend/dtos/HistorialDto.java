package com.backend.dtos;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistorialDto {
	//private Long id;
	private String apodo;
	private List<ReproduccionDto> reproducciones;
}
