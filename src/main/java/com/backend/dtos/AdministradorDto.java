package com.backend.dtos;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AdministradorDto {
	private LocalDateTime fechaRegistro;
	private Long artistaId;
	private Long usuarioId;
	private String nivel;
}
