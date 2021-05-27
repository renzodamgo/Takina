package com.backend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancionMiniDto {
	//private long id;
	private Integer track;
	private String nombre;
	private Float duracion;
}
