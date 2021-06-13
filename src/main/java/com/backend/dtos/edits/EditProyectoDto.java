package com.backend.dtos.edits;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EditProyectoDto {
	private Long id;
	private String nombre;
	private String descripcion;
	private LocalDateTime lanzamiento;
	private String discografica;
	private String fotoPortada;
	private String genero;
}
