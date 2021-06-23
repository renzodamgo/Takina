package com.backend.dtos;

import lombok.Data;

@Data
public class CreditoDto {
	private Long artistaId;
	private Long cancionId;
	private String descripcion;
}
