package com.backend.dtos;

import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvitadoDto {
	//private Long eventoId;
	private String artistaNombre;
	private LocalTime horaInicio;
	private LocalTime horaFin;
}
