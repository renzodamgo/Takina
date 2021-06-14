package com.backend.dtos.creates;

import lombok.Data;

@Data
public class CreateCancionDto {
	private String nombre;
	private Float duracion;
	private String audio;
	private Long proyectoId;
}