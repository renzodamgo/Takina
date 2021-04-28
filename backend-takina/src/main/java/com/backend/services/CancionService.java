package com.backend.services;

import com.backend.dtos.CancionDto;

public interface CancionService {
    CancionDto getCancion(Long cancionId);

}
