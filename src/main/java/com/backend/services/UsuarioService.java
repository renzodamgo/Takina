package com.backend.services;

import java.util.List;

import com.backend.dtos.UsuarioDto;
import com.backend.dtos.ReproduccionDto;
import com.backend.dtos.HistorialDto;
import com.backend.dtos.SeguidorDto;
import com.backend.dtos.creates.CreateUsuarioDto;
import com.backend.exceptions.TakinaException;

public interface UsuarioService {
	// Obtener todos los usuarios
	List<UsuarioDto> getUsuarios() throws TakinaException;
	// Obtener usuario por ID
	UsuarioDto getUsuarioId(Long cancionId) throws TakinaException;
	// Obtener usuario por Apodo
	UsuarioDto getUsuarioApodo(String apodo) throws TakinaException;
	// Obtener usuario por Correo
	UsuarioDto getUsuarioCorreo(String correo) throws TakinaException;
	// Obtener usuario por Nombre
	UsuarioDto getUsuarioNombre(String nombre) throws TakinaException;

	// US001 - Registro
	UsuarioDto createUsuario(CreateUsuarioDto createUsuarioDto) throws TakinaException;

	// US002 - Login
	UsuarioDto loginUsuarioByApodoOrCorreoUsingPassword(String login, String password) throws TakinaException;

	// US021 - Buscar otro usuario por su nombre
	List<UsuarioDto> getUsuariosByNombre(String nombre) throws TakinaException;

	// Crear Reproduccion
	ReproduccionDto createReproduccion(Long usuarioId, Long cancionId) throws TakinaException;

	// US030 - Historial de reproducciones
	HistorialDto getHistorial(Long usuarioId) throws TakinaException;

	// US009 - Creacion de seguidor
	SeguidorDto createSeguidor(Long usuarioId, Long artistaId) throws TakinaException;
	
	// US031 - Eliminacion de seguidor
	void deleteSeguidor(Long usuarioId, Long artistaId) throws TakinaException;
	
	// US032 - Creacion de asistente
	
	// US033 - Eliminacion de asistente
	
	// US011 - Actualizacion a premium
	// ¿¿¿???
}
