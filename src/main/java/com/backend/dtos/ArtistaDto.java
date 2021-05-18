package com.backend.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtistaDto {
	private Long id;
    private String nombre;
    private String fotoPerfil;
    private String fotoPortada;
    private String biografia;
    private Integer seguidoresTotal;
    private Integer oyentesTotal;
	private String departamento;
	private String genero;
	private List<AdministradorDto> administradores;


    // Cuantos oyentes hay al mes
    // query -> tabla_cancion_usuario
    // mes actual -> canciones que pertenezcan al artista -> cantidad unica de usuarios escuchandola
}
