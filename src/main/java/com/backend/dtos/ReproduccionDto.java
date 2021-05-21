package com.backend.dtos;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReproduccionDto {
	//private Long usuarioId;
	//private Long cancionId;
	private String cancionProyectoArtistaNombre;
	private String cancionNombre;
	private LocalDateTime fecha;
}