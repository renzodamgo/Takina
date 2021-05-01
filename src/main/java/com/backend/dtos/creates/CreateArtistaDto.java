package com.backend.dtos.creates;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateArtistaDto {
    private String nombre;
	private String biografia;
    private String foto_perfil;
    private String foto_portada;
	private String departamentoOrigen;
	private String generoMusical;
}