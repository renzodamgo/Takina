package com.backend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtistaDto {
    private String nombre;
    private String foto_perfil;
    private String foto_portada;
    private String biografia;
    private Integer seguidores;
    private Integer oyentes_total;
	private String departamento_origen;
	private String genero_musical;


    // Cuantos oyentes hay al mes
    // query -> tabla_cancion_usuario
    // mes actual -> canciones que pertenezcan al artista -> cantidad unica de usuarios escuchandola
}
