package com.backend.services;

import java.util.List;

import com.backend.dtos.CancionDto;
import com.backend.dtos.creates.CreateCancionDto;
import com.backend.dtos.creates.CreateCancionSencilloDto;
import com.backend.exceptions.TakinaException;

public interface CancionService {
	// Obtener cancion por ID
    CancionDto getCancionId(Long cancionId) throws TakinaException;
	// Obtener cancion por Nombre
	CancionDto getCancionNombre(String nombre) throws TakinaException;
	// Mostrar todas las canciones
    List<CancionDto> getCanciones() throws TakinaException;

	// US ??? - Añadir una cancion a un proyecto musical
	CancionDto createCancion(CreateCancionDto createCancionDto) throws TakinaException;

	// US023 - Como usuario administrador de un perfil de artista Quiero subir una cancion Para que se cree un proyecto musical de sencillo automaticamente
	CancionDto createCancionSencillo(CreateCancionSencilloDto createCancionSencilloDto) throws TakinaException;

    // US006 - Como usuario Quiero buscar una canción Para encontrar la canción que deseo escuchar en ese instante
    List<CancionDto> getCancionesByNombre(String nombre) throws TakinaException;

	// US025 - Como usuario Quiero buscar canciones por su genero musical Para escuchar y armar mi playlist ideal
	List<CancionDto> getCancionesByGeneroMusical(String nombre) throws TakinaException;
	
}
