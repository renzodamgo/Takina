package com.backend.services;

import java.util.List;

import com.backend.dtos.CancionDto;
import com.backend.dtos.EstadisticaDto;
import com.backend.dtos.ReproduccionDto;
import com.backend.dtos.creates.CreateCancionDto;
import com.backend.dtos.creates.CreateCreditoDto;
import com.backend.dtos.creates.CreateCancionProyectoDto;
import com.backend.exceptions.TakinaException;

public interface CancionService {
	// Obtener cancion por ID
	CancionDto getCancionId(Long cancionId) throws TakinaException;
	// Obtener cancion por Nombre
	CancionDto getCancionNombre(String nombre) throws TakinaException;
	// Mostrar todas las canciones
	List<CancionDto> getCanciones() throws TakinaException;

	// Canciones de un proyecto musical
	List<CancionDto> getCancionesByProyectoId(Long proyectoId) throws TakinaException;

	// US ??? - Añadir una cancion a un proyecto musical
	CancionDto createCancion(CreateCancionDto createCancionDto) throws TakinaException;

	// US023 - Como usuario administrador de un perfil de artista Quiero subir una cancion Para que se cree un proyecto musical de sencillo automaticamente
	CancionDto createCancionProyecto(CreateCancionProyectoDto createCancionProyectoDto) throws TakinaException;

	// US006 - Como usuario Quiero buscar una canción Para encontrar la canción que deseo escuchar en ese instante
	List<CancionDto> getCancionesByNombre(String nombre) throws TakinaException;

	// US025 - Como usuario Quiero buscar canciones por su genero musical Para escuchar y armar mi playlist ideal
	List<CancionDto> getCancionesByGeneroMusical(String nombre) throws TakinaException;

	// US038 - Eliminar cancion de proyecto
	void deleteCancionById(Long cancionId) throws TakinaException;

	// US040 - Crear credito
	CancionDto createCredito(CreateCreditoDto createCreditoDto) throws TakinaException;

	// Crear Reproduccion
	ReproduccionDto createReproduccion(Long usuarioId, Long cancionId) throws TakinaException;

	// US030 - Historial de canciones
	List<ReproduccionDto> getHistorial(Long usuarioId) throws TakinaException;

	// US029 - revisar las reproducciones en total que tiene un artista
	EstadisticaDto getReproduccionesByArtistaIdAndDate(Long artistaId, Integer indice) throws TakinaException;
}