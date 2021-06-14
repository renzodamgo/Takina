package com.backend.dtos.creates;

import lombok.Data;

@Data
public class CreateMercanciaDto {
	private String nombre;
	private String descripcion;
	private String foto;
	private Float precio;
	private Long artistaId;
}
