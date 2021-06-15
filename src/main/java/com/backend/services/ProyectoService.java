
package com.backend.services;

import java.util.List;

import com.backend.dtos.ProyectoDto;
import com.backend.dtos.ProyectoMiniDto;
import com.backend.dtos.creates.CreateProyectoDto;
import com.backend.dtos.edits.EditProyectoDto;
import com.backend.exceptions.TakinaException;

public interface ProyectoService {
	// Obtener proyecto ID
	ProyectoDto getProyectoById(Long cancionId) throws TakinaException;

	// Obtener proyecto por Nombre
	ProyectoDto getProyectoByNombre(String nombre) throws TakinaException;

	// Mostrar todos los proyectos
	List<ProyectoDto> getProyectos() throws TakinaException;

    // US022 - Busqueda de proyecto musical
    List<ProyectoDto> getProyectosByNombre(String nombre) throws TakinaException;

    // US014 - Como usuario administrador de un perfil de artista Quiero publicar proyectos  a la plataforma Para promocionar mi música públicamente
    ProyectoDto createProyecto(CreateProyectoDto createProyectoDto) throws TakinaException;

	// US039 - Editar proyecto musical
	ProyectoDto editProyecto(EditProyectoDto editProyectoDto) throws TakinaException;

	// Obtener todos los proyectos de un artista
    List<ProyectoDto> getProyectosByArtistaId(Long artistaId) throws TakinaException;

	// Busqueda de proyectos por genero musical
    List<ProyectoDto> getProyectosByGenero(String genero) throws TakinaException;

	// US003 - Noticias de Proyectos Musicales
	List<ProyectoMiniDto> getUltimos10ProyectosSubidos() throws TakinaException;

	// US042 - Eliminar proyecto y sus canciones
	void deleteProyectoById(Long protectoId) throws TakinaException;

}