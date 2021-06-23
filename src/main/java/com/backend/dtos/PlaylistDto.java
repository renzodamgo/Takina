package com.backend.dtos;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PlaylistDto {
	private Float duracion;
	private Integer numCanciones;
	private LocalDateTime creacion;
	private Long id;
	private Long usuarioId;
	private String descripcion;
	private String nombre;
}
