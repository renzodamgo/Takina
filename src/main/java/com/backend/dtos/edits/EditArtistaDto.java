package com.backend.dtos.edits;

import lombok.Data;

@Data
public class EditArtistaDto {
    private Long id;
	private String nombre;
	private String fotoPerfil;
	private String fotoPortada;
	private String biografia;
    private String departamento;
    private String genero;
}
