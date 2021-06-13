package com.backend.dtos;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ListadoDto {
	//private Long playlistId;
	private Long cancionId;
	private String cancionNombre;
	private LocalDateTime fechaAdicion;
}
