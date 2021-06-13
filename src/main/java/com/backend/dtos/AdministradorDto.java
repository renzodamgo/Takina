package com.backend.dtos;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AdministradorDto {
	//private Long usuarioId;
	private String usuarioNombre;
	private String nivel;
	private LocalDateTime fechaRegistro;
}
