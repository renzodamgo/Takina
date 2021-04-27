package com.backend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto {
    private Long id;
    private String apodo;
    private String nombre;
    private String correo;
    private String fotoPerfil;
    private boolean premium;

}
