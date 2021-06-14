package com.backend.dtos;

import java.util.List;
import lombok.Data;

@Data
public class HistorialDto {
	//private Long id;
	private String apodo;
	private List<ReproduccionDto> reproducciones;
}
