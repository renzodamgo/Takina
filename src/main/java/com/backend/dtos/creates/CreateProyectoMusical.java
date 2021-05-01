package com.backend.dtos.creates;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreateProyectoMusical {
	private String nombre;
	private Float duracion;
	private String descripcion;
	private LocalDateTime fecha;
	private Integer canciones;
	private String discografica;
	private Long artista_id;
}