package com.backend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditoDto {
	private Long artistaId;
    private Long cancionId;
	private String descripcion;
}
