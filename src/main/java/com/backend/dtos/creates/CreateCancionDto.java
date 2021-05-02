package com.backend.dtos.creates;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCancionDto {
    private String nombre;
	private Float duracion;
    private String audio;
    private LocalDateTime lanzamiento;
	private String generoMusical;
	private Long proyectoId;
}