package com.backend.services;

import java.util.List;

import com.backend.dtos.UsuarioDto;
import com.backend.dtos.creates.CreateUsuarioDto;
import com.backend.exceptions.TakinaException;

public interface UsuarioService {
    UsuarioDto getUsuarioId(Long cancionId) throws TakinaException;
	UsuarioDto getUsuarioApodo(String apodo) throws TakinaException;
	UsuarioDto getUsuarioCorreo(String correo) throws TakinaException;

    List<UsuarioDto> getUsuarios() throws TakinaException;

	// US001 - Como internauta Quiero registrarme en la aplicación Para usar la Plataforma
	UsuarioDto createUsuario(CreateUsuarioDto createUsuarioDto) throws TakinaException;

	// US021 - Como usuario Quiero buscar a otro usuario por su apodo Para ver su perfil y conocer más de el/ella. 
    UsuarioDto getUsuarioNombre(String nombre) throws TakinaException;

	// US002 - Como usuario registrado Quiero ingresar a la aplicacion Para utilizar la plataforma
	// Reparar
	UsuarioDto loginUsuarioByApodoOrCorreoUsingPassword(String login, String password) throws TakinaException;
}
