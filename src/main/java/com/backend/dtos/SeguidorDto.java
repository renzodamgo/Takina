package com.backend.dtos;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SeguidorDto {
	private String usuarioApodo;
	private LocalDateTime fecha;
}
