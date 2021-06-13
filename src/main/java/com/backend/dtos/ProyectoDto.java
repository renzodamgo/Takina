package com.backend.dtos;
import java.time.LocalDateTime;

import java.util.List;
import lombok.Data;

@Data
public class ProyectoDto {
	private Long id;
	private String nombre;
	private String tipo;
	private Float duracion;
	private String descripcion;
	private Integer numCanciones;
	private LocalDateTime lanzamiento;
	private LocalDateTime fecha;
	private String discografica;
	private String fotoPortada;
	private String genero;
	private Long artistaId;
	private List<CancionMiniDto> canciones;
}