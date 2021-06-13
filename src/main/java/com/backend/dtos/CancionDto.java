package com.backend.dtos;

import java.util.List;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CancionDto {
	private Long id;
	private String nombre;
	private String audio;
	private String fotoPortada;
	private LocalDateTime lanzamiento;
	private String genero;
	private Float duracion;
	private Long proyectoId;
	private List<CreditoDto> creditos;
}
