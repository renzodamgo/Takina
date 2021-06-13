package com.backend.dtos;

import lombok.Data;

@Data
public class MercanciaDto {
	private Long id;
	private String nombre;
	private Float precio;
	private String foto;
	private String descripcion;
	private Long artistaId;
}
