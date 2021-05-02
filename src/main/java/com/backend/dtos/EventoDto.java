package com.backend.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventoDto {
    private String nombre;
    private String lugar;
	private String departamento;
    private LocalDateTime fecha;
    private Float precio;
    private String fotoPortada;
    private Integer interesados;
}
