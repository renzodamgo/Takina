package com.backend.dtos.creates;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMercanciaDto {
    private String nombre;
    private Float precio;
    private String foto;
	private Long artista_id;
}
