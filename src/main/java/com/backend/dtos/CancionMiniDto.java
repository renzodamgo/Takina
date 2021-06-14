package com.backend.dtos;

import lombok.Data;

@Data
public class CancionMiniDto {
	//private long id;
	private Integer track;
	private String nombre;
	private Float duracion;
}
