package com.takina.artist.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArtistaDto {
	private Long id;
	private String nombre;
	private String fotoPerfil;
	private String fotoPortada;
	private String biografia;
	private Integer totalSeguidores;
	private Integer totalReproducciones;
	private String departamento;
	private String genero;
	private List<AdministradorDto> administradores;
	private List<SeguidorDto> seguidores; 
}