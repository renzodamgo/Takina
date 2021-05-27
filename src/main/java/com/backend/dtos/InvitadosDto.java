package com.backend.dtos;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvitadosDto {
	private String nombre;
	private List<InvitadoDto> invitados;
}
