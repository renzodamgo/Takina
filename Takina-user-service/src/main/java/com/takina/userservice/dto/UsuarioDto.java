package com.takina.userservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UsuarioDto {
    private Long id;
    private String apodo;
    private String correo;
    private String password;
    private String nombre;
    private String fotoPerfil;
    private boolean premium;
    private LocalDateTime ultimoIngreso;
    private List<PlaylistMiniDto> playlists;
}
