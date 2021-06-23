package com.backend.dtos;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ReproduccionDto {
	private Long usuarioId;
	private Long cancionId;
	private String cancionProyectoArtistaNombre;
	private String cancionNombre;
	private LocalDateTime fecha;
}