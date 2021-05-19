package com.backend.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministradorDto {
	private Long usuarioId;
	private Long artistaId;
	private String nivel;
	private LocalDateTime fechaRegistro;
}
