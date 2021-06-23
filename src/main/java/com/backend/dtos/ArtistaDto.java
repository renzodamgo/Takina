package com.backend.dtos;

import lombok.Data;

@Data
public class ArtistaDto {
	private Long id;
	private Long totalReproducciones;
	private Long totalSeguidores;
	private String biografia;
	private String departamento;
	private String fotoPerfil;
	private String fotoPortada;
	private String genero;
	private String nombre;
}