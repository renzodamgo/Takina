package com.backend.services.impl;

import com.backend.dtos.ArtistaDto;
import com.backend.entities.Artista;
import com.backend.exceptions.NotFoundException;
import com.backend.exceptions.TakinaException;
import com.backend.repositories.ArtistaRepository;
import com.backend.services.ArtistaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ArtistaServiceImpl implements ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;
    private static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public ArtistaDto getArtista(Long Id) throws TakinaException{
        return modelMapper.map(getArtistaEntity(Id), ArtistaDto.class);
    }

    private Artista getArtistaEntity(Long ArtistaId) throws TakinaException {
        return artistaRepository.findById(ArtistaId)
                .orElseThrow(()-> new NotFoundException("NOTFOUND-404","ARTISTA_NOTFOUND-404"));
    }



}
