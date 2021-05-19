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
	private Integer seguidoresTotal;
	private Integer reproduccionesTotal;
	private String departamento;
	private String genero;
	private List<AdministradorDto> administradores;
	private List<ProyectoMiniDto> proyectos;
}
