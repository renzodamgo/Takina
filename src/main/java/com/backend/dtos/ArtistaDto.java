package com.backend.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

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
	private List<ProyectoMiniDto> proyectos;
	private List<SeguidorDto> seguidores; 
}