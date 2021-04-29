package com.backend.services;

import java.util.List;

import com.backend.dtos.ArtistaDto;
import com.backend.dtos.creates.CreateArtistaDto;
import com.backend.exceptions.TakinaException;

public interface ArtistaService {
    ArtistaDto getArtista(Long Id) throws TakinaException;

    List<ArtistaDto> getArtistas() throws TakinaException;

    // Como usuario Quiero buscar a un artista por su nombre para ver y escuchar su musica y su contenido
    ArtistaDto getArtistaNombre(String nombre) throws TakinaException;

    // Como usuario quiero crearme un "artista" para poder compartir mi musica y mis proyectos con los demas
    ArtistaDto createArtista(CreateArtistaDto createArtistaDto) throws TakinaException;
}
