package com.backend.dtos.creates;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCancionDto {
    private String nombre;
	private Float duracion;
    private String audio;
	private Long proyectoId;
}