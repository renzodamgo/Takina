package com.backend.dtos;

import java.time.LocalTime;
import lombok.Data;

@Data
public class InvitadoDto {
	private Long eventoId;
	private Long artistaId;
	private LocalTime horaInicio;
	private LocalTime horaFin;
}
