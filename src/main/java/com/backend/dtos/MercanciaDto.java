package com.backend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MercanciaDto {
    private String nombre;
    private Float precio;
    private String foto;
	private Long artistaId;
}
