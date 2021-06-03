package com.takina.userservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ListadoDto {
    private Long cancionId;
    private String cancionNombre;
    private LocalDateTime fechaAdicion;
}
