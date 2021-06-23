package com.backend.dtos;

import lombok.Data;

@Data
public class MercanciaDto {
	private Float precio;
	private Long artistaId;
	private Long id;
	private String descripcion;
	private String foto;
	private String nombre;
}
