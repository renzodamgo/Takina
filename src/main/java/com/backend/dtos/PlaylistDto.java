package com.backend.dtos;

import java.util.List;
import java.time.LocalDateTime;
import lombok.Data;

@Data
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
