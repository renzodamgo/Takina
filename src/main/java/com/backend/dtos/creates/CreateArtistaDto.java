package com.backend.dtos.creates;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateArtistaDto {
	private String nombre;
	private String biografia;
	private String fotoPerfil;
	private String fotoPortada;
	private String departamento;
	private String genero;
	private Long usuarioId;
}