package com.backend.dtos.creates;

import lombok.Data;

@Data
public class CreateArtistaDto {
	private String nombre;
	private String biografia;
	private String fotoPerfil;
	private String fotoPortada;
	private String departamento;
	private String genero;
	private Long usuarioId;
}