package com.backend.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListaReproduccionDto {
	private LocalDateTime creacion;
	private Integer numeroCanciones;
	private Float duracion; // decimales a hora
}
