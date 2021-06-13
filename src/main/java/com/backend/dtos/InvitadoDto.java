package com.backend.dtos;

import java.time.LocalTime;
import lombok.Data;

@Data
public class InvitadoDto {
	//private Long eventoId;
	private String artistaNombre;
	private LocalTime horaInicio;
	private LocalTime horaFin;
}
