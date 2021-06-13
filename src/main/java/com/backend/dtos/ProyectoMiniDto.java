package com.backend.dtos;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ProyectoMiniDto {
	private Long id;
	private String nombre;
	private LocalDateTime lanzamiento;
	private LocalDateTime fecha;
}
