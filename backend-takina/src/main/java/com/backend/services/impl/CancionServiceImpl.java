package com.backend.services.impl;

import com.backend.dtos.CancionDto;
import com.backend.entities.Cancion;
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
    public CancionDto getCancion(Long cancionId) {
        return modelMapper.map(getCancionEntity(cancionId),CancionDto.class);

    }


    private Cancion getCancionEntity(Long cancionId) {
        return cancionRepository.findById(cancionId);
    }
}
