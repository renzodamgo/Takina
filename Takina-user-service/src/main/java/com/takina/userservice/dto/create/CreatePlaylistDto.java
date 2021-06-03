package com.takina.userservice.dto.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePlaylistDto {
	private String nombre;
	private String descripcion;
	private Long usuarioId;
}
