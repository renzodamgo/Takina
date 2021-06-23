package com.backend.dtos;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AsistenteDto {
	private Long usuarioId;
	private Long eventoId;
	private LocalDateTime fecha;
}
