package com.backend.dtos.creates;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProyectoMusicalDto {
    private String nombre;
	private String tipoProyecto;
	private Float duracion;
	private String descripcion;
	private LocalDateTime fechaLanzamiento;
	private Integer canciones;
	private String discografica;
	private Long artista_id;
	private String rutaImagen;
}