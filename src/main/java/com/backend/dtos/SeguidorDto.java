package com.backend.dtos;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SeguidorDto {
	private Long usuarioId;
	private Long artistaId;
	private LocalDateTime fecha;
}
