package com.backend.dtos;

import java.util.List;

import lombok.Data;

@Data
public class ArtistaDto {
	private Long id;
	private String nombre;
	private String fotoPerfil;
	private String fotoPortada;
	private String biografia;
	private Long totalSeguidores;
	private Long totalReproducciones;
	private String departamento;
	private String genero;
	private List<AdministradorDto> administradores;
	private List<ProyectoMiniDto> proyectos;
	private List<SeguidorDto> seguidores; 
}