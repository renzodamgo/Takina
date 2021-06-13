package com.takina.artist.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SeguidorDto {
	private String usuarioApodo;
	private LocalDateTime fecha;
}
