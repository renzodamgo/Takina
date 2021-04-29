package com.backend.services.impl;

import com.backend.dtos.CancionDto;
import com.backend.entities.Cancion;
import com.backend.exceptions.NotFoundException;
import com.backend.exceptions.TakinaException;
import com.backend.repositories.CancionRepository;
import com.backend.services.CancionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancionServiceImpl implements CancionService {


    @Autowired
    private CancionRepository cancionRepository;
    private static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public CancionDto getCancion(Long cancionId) throws TakinaException {
        return modelMapper.map(getCancionEntity(cancionId),CancionDto.class);

    }


    private Cancion getCancionEntity(Long cancionId) throws TakinaException {
        return cancionRepository.findById(cancionId)
                .orElseThrow(()-> new NotFoundException("NOTFOUND-404","CANCION_NOTFOUND-404"));
    }
}
