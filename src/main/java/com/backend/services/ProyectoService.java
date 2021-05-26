
package com.backend.services;

import java.util.List;

import com.backend.dtos.ProyectoDto;
import com.backend.dtos.creates.CreateProyectoDto;
import com.backend.exceptions.TakinaException;

public interface ProyectoService {

	// Obtener proyecto ID
	ProyectoDto getProyectoById(Long cancionId) throws TakinaException;
	// Obtener proyecto por Nombre
	ProyectoDto getProyectoByNombre(String nombre) throws TakinaException;

	// Mostrar todos los proyectos
	List<ProyectoDto> getProyectos() throws TakinaException;

    // US022 - Como usuario Quiero buscar un proyecto  según una porción de texto Para revisar la produccion  en ese instante
    List<ProyectoDto> getProyectosByNombre(String nombre) throws TakinaException;

    // US014 - Como usuario administrador de un perfil de artista Quiero publicar proyectos  a la plataforma Para promocionar mi música públicamente
    ProyectoDto createProyecto(CreateProyectoDto createProyectoDto) throws TakinaException;

    ProyectoDto replaceDescription(ProyectoDto proyectoDto) throws TakinaException;

    List<ProyectoDto> getProyectosByAritstaId(Long aritstaId) throws TakinaException;

    List<ProyectoDto> getProyectosByGenero(String genero) throws TakinaException;
}