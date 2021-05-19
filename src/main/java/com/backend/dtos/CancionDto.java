package com.backend.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CancionDto {
	private String nombre;
	private String audio;
	private String fotoPortada;
	private LocalDateTime lanzamiento;
	private String genero;
	private Float duracion;
	private Long proyectoId;
	private List<CreditoDto> creditos;
}
