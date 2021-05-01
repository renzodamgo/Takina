package com.backend.dtos.creates;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateCancionDto {
    private String nombre;
    private String audio;
    private String imagen;
    private LocalDateTime lanzamiento;
	private String genero_musical;

}