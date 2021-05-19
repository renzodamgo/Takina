package com.backend.services;

import java.util.List;

import com.backend.dtos.UsuarioDto;
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

	// US001 - Como internauta Quiero registrarme en la aplicación Para usar la Plataforma
	UsuarioDto createUsuario(CreateUsuarioDto createUsuarioDto) throws TakinaException;

	// US002 - Como usuario registrado Quiero ingresar a la aplicacion Para utilizar la plataforma // Reparar
	UsuarioDto loginUsuarioByApodoOrCorreoUsingPassword(String login, String password) throws TakinaException;

	// US021 - Como usuario Quiero buscar a otro usuario por su nombre Para encontrar su perfil y conocer más de el/ella. 
	List<UsuarioDto> getUsuariosByNombre(String nombre) throws TakinaException;
}
