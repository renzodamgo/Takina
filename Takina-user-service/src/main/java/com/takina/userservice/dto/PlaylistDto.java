package com.takina.userservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PlaylistDto {
	private Long id;
	private String nombre;
	private String descripcion;
	private LocalDateTime creacion;
	private Float duracion;
	private Integer numCanciones;
	private Long usuarioId;
	private List<ListadoDto> listados;
}
