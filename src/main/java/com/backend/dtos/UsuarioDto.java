package com.backend.dtos;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UsuarioDto {
	private Long id;
	private String apodo;
	private String correo;
	private String password;
	private String nombre;
	private String fotoPerfil;
	private boolean premium;
	private LocalDateTime ultimoIngreso;
}
