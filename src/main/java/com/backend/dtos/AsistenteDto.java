package com.backend.dtos;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AsistenteDto {
	//private Long usuarioId;
	private String usuarioApodo;
	private LocalDateTime fecha;
}
