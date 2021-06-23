package com.backend.dtos;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class EventoDto {
	private Float precio;
	private Integer interesados;
	private LocalDateTime fecha;
	private Long id;
	private String departamento;
	private String descripcion;
	private String direccion;
	private String fotoPortada;
	private String lugar;
	private String nombre;
}
