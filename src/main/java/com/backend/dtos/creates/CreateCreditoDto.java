package com.backend.dtos.creates;
import lombok.Data;

@Data
public class CreateCreditoDto {
		private Long artistaId;
		private Long cancionId;
		private String descripcion;
}
