package com.backend.dtos.creates;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateEventoDto {
	private String nombre;
	private String descripcion;
	private String lugar;
	private String direccion;
	private String departamento;
	private LocalDateTime fecha;
	private Float precio;
	private String fotoPortada;
	//private Integer interesados;
}
