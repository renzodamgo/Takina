package com.backend.dtos.edits;

import lombok.Data;

@Data
public class EditUsuarioDto {
	private Long id;
	private String apodo;
	private String correo;
	private String fotoPerfil;
	private String nombre;
	private String password;
}
