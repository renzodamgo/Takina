package com.backend.dtos;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class EventoDto {
	private Long id;
	private String nombre;
	private String descripcion;
	private String lugar;
	private String direccion;
	private String departamento;
	private LocalDateTime fecha;
	private Float precio;
	private String fotoPortada;
	private Integer interesados;
	private List<AsistenteDto> asistentes;
	private List<InvitadoDto> invitados;
}
