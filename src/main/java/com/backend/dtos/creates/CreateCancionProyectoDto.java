package com.backend.dtos.creates;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CreateCancionProyectoDto {
	private String nombre;
	private Float duracion;
	private String audio;
	private String descripcion;
	private LocalDateTime lanzamiento;
	private String discografica;
	private String fotoPortada;
	private Long artistaId;
}
