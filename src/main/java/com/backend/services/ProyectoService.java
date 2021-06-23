
package com.backend.services;

import java.util.List;

import com.backend.dtos.ProyectoDto;
import com.backend.dtos.creates.CreateProyectoDto;
import com.backend.dtos.edits.EditProyectoDto;
import com.backend.exceptions.TakinaException;

public interface ProyectoService {
	List<ProyectoDto> getProyectos() throws TakinaException;
	List<ProyectoDto> getProyectosByArtistaId(Long artistaId) throws TakinaException;
	List<ProyectoDto> getProyectosByGenero(String genero) throws TakinaException;
	List<ProyectoDto> getProyectosByNombre(String nombre) throws TakinaException;
	List<ProyectoDto> getUltimos10ProyectosSubidos() throws TakinaException;
	ProyectoDto createProyecto(CreateProyectoDto createProyectoDto) throws TakinaException;
	ProyectoDto editProyecto(EditProyectoDto editProyectoDto) throws TakinaException;
	ProyectoDto getProyectoById(Long cancionId) throws TakinaException;
	ProyectoDto getProyectoByNombre(String nombre) throws TakinaException;
	void deleteProyectoById(Long protectoId) throws TakinaException;
}