package com.backend.dtos.creates;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateEventoDto {
    private String nombre;
    private String lugar;
    private LocalDateTime fecha;
    private Float precio;
    private String fotoPortada;
    private Integer interesados;
}
