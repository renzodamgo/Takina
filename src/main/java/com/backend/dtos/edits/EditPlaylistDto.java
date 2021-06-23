package com.backend.dtos.edits;

import lombok.Data;

@Data
public class EditPlaylistDto {
    private Long id;
	private String nombre;
	private String descripcion;
}
