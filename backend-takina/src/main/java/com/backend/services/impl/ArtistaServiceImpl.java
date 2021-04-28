package com.backend.services.impl;

import com.backend.dtos.ArtistaDto;
import com.backend.entities.Artista;
import com.backend.repositories.ArtistaRepository;
import com.backend.services.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;

public class ArtistaServiceImpl implements ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;

    @Override
    public ArtistaDto getArtista(Long Id){
        return
    }

    private Artista getArtistaEntity(Long ArtistaId){
        return artistaRepository.findById(ArtistaId);
    }



}
