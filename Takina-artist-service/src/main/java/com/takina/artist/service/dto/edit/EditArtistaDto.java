package com.takina.artist.service.dto.edit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditArtistaDto {
    private Long id;
	private String nombre;
	private String fotoPerfil;
	private String fotoPortada;
	private String biografia;
    private String departamento;
    private String genero;
}
