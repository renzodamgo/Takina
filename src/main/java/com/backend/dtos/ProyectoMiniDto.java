package com.backend.dtos;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProyectoMiniDto {
	//private Long id;
	private String nombre;
	private LocalDateTime lanzamiento;
}
