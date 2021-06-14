package com.backend.dtos;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AsistenteDto {
	//private Long usuarioId;
	private String usuarioApodo;
	private LocalDateTime fecha;
}
