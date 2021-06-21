package com.backend.services;

import java.util.List;

import com.backend.dtos.CancionDto;
import com.backend.dtos.EstadisticaDto;
import com.backend.dtos.ReproduccionDto;
import com.backend.dtos.creates.CreateCancionDto;
import com.backend.dtos.creates.CreateCancionProyectoDto;
import com.backend.dtos.creates.CreateCreditoDto;
import com.backend.exceptions.TakinaException;

public interface CancionService {
	CancionDto createCancion(CreateCancionDto createCancionDto) throws TakinaException;
	CancionDto createCancionProyecto(CreateCancionProyectoDto createCancionProyectoDto) throws TakinaException;
	CancionDto createCredito(CreateCreditoDto createCreditoDto) throws TakinaException;
	CancionDto getCancionId(Long cancionId) throws TakinaException;
	CancionDto getCancionNombre(String nombre) throws TakinaException;
	EstadisticaDto getReproduccionesByArtistaIdAndDate(Long artistaId, Integer indice) throws TakinaException;
	List<CancionDto> getCanciones() throws TakinaException;
	List<CancionDto> getCancionesByGeneroMusical(String nombre) throws TakinaException;
	List<CancionDto> getCancionesByNombre(String nombre) throws TakinaException;
	List<CancionDto> getCancionesByProyectoId(Long proyectoId) throws TakinaException;
	List<ReproduccionDto> getHistorial(Long usuarioId) throws TakinaException;
	ReproduccionDto createReproduccion(Long usuarioId, Long cancionId) throws TakinaException;
	void deleteCancionById(Long cancionId) throws TakinaException;
}