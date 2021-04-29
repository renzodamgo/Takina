package com.backend.dtos.creates;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateArtistaDto {
    private String nombre;
    private String foto_perfil;
    private String foto_portada;
    private String biografia;
    private Integer seguidores;
    private Integer oyentesTotal;
	private String departamento_origen;
	private String genero_musical;
}