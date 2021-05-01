package com.backend.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProyectoMusicalDto {
    private String nombre;
	private String tipoProyecto;
	private Float duracion;
	private String descripcion;
	private LocalDateTime lanzamiento;
	private Integer canciones;
	private String discografica;
	private Long artista_id;
	private String rutaImagen;
}
