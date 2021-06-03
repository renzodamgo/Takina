package com.takina.userservice.dto.edit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditUsuarioDto {
    private Long id;
    private String password;
    private String nombre;
    private String correo;
    private String fotoPerfil;
}