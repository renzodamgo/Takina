package com.backend.dtos.creates;

import lombok.Data;

@Data
public class CreateUsuarioDto {
	private String apodo;
	private String password;
	private String nombre;
	private String correo;
	private String fotoPerfil;
}