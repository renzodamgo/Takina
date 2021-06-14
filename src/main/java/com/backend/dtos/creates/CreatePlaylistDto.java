package com.backend.dtos.creates;

import lombok.Data;

@Data
public class CreatePlaylistDto {
	private String nombre;
	private String descripcion;
	private Long usuarioId;
}
