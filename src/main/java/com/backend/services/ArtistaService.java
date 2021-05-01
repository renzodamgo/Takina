package com.backend.services;

import java.util.List;

import com.backend.dtos.ArtistaDto;
import com.backend.dtos.creates.CreateArtistaDto;
import com.backend.exceptions.TakinaException;

public interface ArtistaService {
    ArtistaDto getArtista(Long Id) throws TakinaException;
    List<ArtistaDto> getArtistas() throws TakinaException;
    List<ArtistaDto> getArtistasByName(String nombre) throws TakinaException;


    List<ArtistaDto> getArtistasByGeneroMusical(String genero_musical) throws TakinaException;


    List<ArtistaDto> getArtistasByDepartamento(String departamento_origen) throws TakinaException;

    // US010 - Como usuario Quiero buscar a un artista por su nombre para ver y escuchar su musica y su contenido
    ArtistaDto getArtistaNombre(String nombre) throws TakinaException;

    // US013 - Como usuario Quiero registrar un perfil de Músico Para subir contenido musical propio y que esté disponible en la plataforma
    ArtistaDto createArtista(CreateArtistaDto createArtistaDto) throws TakinaException;
}
