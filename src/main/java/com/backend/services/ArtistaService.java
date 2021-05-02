package com.backend.services;

import java.util.List;

import com.backend.dtos.ArtistaDto;
import com.backend.dtos.creates.CreateArtistaDto;
import com.backend.exceptions.TakinaException;

public interface ArtistaService {
	// Obtener artista por el id
    ArtistaDto getArtista(Long Id) throws TakinaException;
	// Lista de todos los artistas
	List<ArtistaDto> getArtistas() throws TakinaException;
	// Obtener artista por su nombre (no case-sensitive)
	ArtistaDto getArtistaNombre(String nombre) throws TakinaException;

	// US004 - Buscar artista por genero musical
    List<ArtistaDto> getArtistasByGeneroMusical(String genero_musical) throws TakinaException;

	// US005 - Buscar artista por departamento origen
    List<ArtistaDto> getArtistasByDepartamento(String departamento_origen) throws TakinaException;

    // US010 - Como usuario Quiero buscar a un artista por su nombre para ver y escuchar su musica y su contenido
	List<ArtistaDto> getArtistasByNombre(String nombre) throws TakinaException;
	// Busqueda profunda de nombre de artista (sin tildes, enyes, mayusculas)
	List<ArtistaDto> searchArtistasByNombre(String nombre) throws TakinaException;

    // US013 - Como usuario Quiero registrar un perfil de Músico Para subir contenido musical propio y que esté disponible en la plataforma
    ArtistaDto createArtista(CreateArtistaDto createArtistaDto) throws TakinaException;
}
