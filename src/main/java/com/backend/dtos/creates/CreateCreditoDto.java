package com.backend.dtos.creates;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCreditoDto {
		private Long artistaId;
		private Long cancionId;
		private String descripcion;
}
