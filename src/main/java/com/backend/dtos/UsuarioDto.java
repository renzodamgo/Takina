package com.backend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto {
    private String apodo;
    private String nombre;
    private String fotoPerfil;
    private boolean premium;
}
