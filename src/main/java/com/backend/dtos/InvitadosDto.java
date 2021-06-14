package com.backend.dtos;

import java.util.List;
import lombok.Data;

@Data
public class InvitadosDto {
	private String nombre;
	private List<InvitadoDto> invitados;
}
