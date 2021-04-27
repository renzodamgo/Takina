package com.backend.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListaRerpoduccionDto {
    private Long id;
    private LocalDateTime creacion;
    private Integer numeroCanciones;
}
