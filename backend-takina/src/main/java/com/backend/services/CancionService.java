package com.backend.services;

import com.backend.dtos.CancionDto;
import com.backend.exceptions.TakinaException;

public interface CancionService {
    CancionDto getCancion(Long cancionId) throws TakinaException;

}
