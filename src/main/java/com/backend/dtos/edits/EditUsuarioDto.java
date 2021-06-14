package com.backend.dtos.edits;

import lombok.Data;

@Data
public class EditUsuarioDto {
	private Long id;
	private String password;
	private String nombre;
	private String correo;
	private String fotoPerfil;
}
