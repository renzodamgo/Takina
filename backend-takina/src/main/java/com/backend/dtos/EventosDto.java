package com.backend.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventosDto {
    private Long id;
    private String nombre;
    private String lugar;
    private LocalDateTime fecha;
    private String portada;

}
