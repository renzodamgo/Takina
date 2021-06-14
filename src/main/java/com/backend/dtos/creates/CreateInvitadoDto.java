package com.backend.dtos.creates;

import java.time.LocalTime;
import lombok.Data;

@Data
public class CreateInvitadoDto {
	private Long eventoId;
	private Long artistaId;
	private LocalTime horaInicio;
	private LocalTime horaFin;
}
