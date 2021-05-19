package com.backend.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaylistDto {
	private String nombre;
	private String descripcion;
	private LocalDateTime creacion;
	private Float duracion;
	private Integer numeroCanciones;
	private Long usuarioId;
}
