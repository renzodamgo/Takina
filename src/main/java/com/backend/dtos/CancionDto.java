package com.backend.dtos;


import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
public class CancionDto {
    private String nombre;
    private String audio;
    private String rutaImagen;
    private LocalDateTime lanzamiento;
	private String generoMusical;
	private Float duracion;
	private Long proyectoId;
}
