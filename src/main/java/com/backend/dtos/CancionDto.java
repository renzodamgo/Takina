package com.backend.dtos;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CancionDto {
	private Float duracion;
	private Integer track;
	private LocalDateTime lanzamiento;
	private Long id;
	private Long proyectoId;
	private String audio;
	private String fotoPortada;
	private String genero;
	private String nombre;
}
