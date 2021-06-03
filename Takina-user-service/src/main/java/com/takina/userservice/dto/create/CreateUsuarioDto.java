package com.takina.userservice.dto.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreateUsuarioDto {
    private String apodo;
    private String password;
    private String nombre;
    private String correo;
    private String fotoPerfil;
}