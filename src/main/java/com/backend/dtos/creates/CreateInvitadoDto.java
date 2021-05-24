package com.backend.dtos.creates;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class CreateInvitadoDto {
	private Long eventoId;
	private Long artistaId;
	private LocalTime horaInicio;
	private LocalTime horaFin;
}
