package com.backend.dtos.creates;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProyectoDto {
	private String nombre;
	private String tipo;
	private Float duracion;
	private String descripcion;
	private LocalDateTime lanzamiento;
	private String discografica;
	private String fotoPortada;
	private String genero;
	private Long artistaId;
}