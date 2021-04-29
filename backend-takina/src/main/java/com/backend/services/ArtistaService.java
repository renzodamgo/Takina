package com.backend.services;

import com.backend.dtos.ArtistaDto;
import com.backend.exceptions.TakinaException;

public interface ArtistaService {
    ArtistaDto getArtista(Long Id) throws TakinaException;
}
