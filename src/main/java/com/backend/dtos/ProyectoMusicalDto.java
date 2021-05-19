package com.backend.dtos;
import java.time.LocalDateTime;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProyectoMusicalDto {
	private Long id;
	private String nombre;
	private String tipo;
	private Float duracion;
	private String descripcion;
	private Integer numCanciones;
	private LocalDateTime lanzamiento;
	private String discografica;
	private String fotoPortada;
	private String genero;
	private Long artistaId;
	private List<CancionMiniDto> canciones;
}