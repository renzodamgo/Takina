package com.backend.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto {
    private String apodo;
	private String correo;
	private String password;
    private String nombre;
    private String fotoPerfil;
    private boolean premium;
	private LocalDateTime ultimoIngreso;
}
