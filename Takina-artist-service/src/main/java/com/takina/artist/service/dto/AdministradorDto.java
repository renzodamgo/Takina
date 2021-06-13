package com.takina.artist.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AdministradorDto {
	//private Long usuarioId;
	private String usuarioNombre;
	private String nivel;
	private LocalDateTime fechaRegistro;
}
