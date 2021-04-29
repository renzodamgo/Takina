package com.backend.services;

import java.util.List;

import com.backend.dtos.CancionDto;
import com.backend.dtos.creates.CreateCancionDto;
import com.backend.exceptions.TakinaException;

public interface CancionService {
    CancionDto getCancionId(Long cancionId) throws TakinaException;

    List<CancionDto> getCanciones() throws TakinaException;

    // US006 Como usuario Quiero buscar una canción para encontrar la canción que deseo escuchar en ese instante
    CancionDto getCancionNombre(String nombre) throws TakinaException;

    // Como usuario musico quiero crear una cancion para que los oyentes puedan escucharlas 
    CancionDto createCancion(CreateCancionDto createCancionDto) throws TakinaException;

}
