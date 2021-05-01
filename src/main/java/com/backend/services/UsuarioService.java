package com.backend.services;

import java.util.List;

import com.backend.dtos.UsuarioDto;
import com.backend.dtos.creates.CreateUsuarioDto;
import com.backend.exceptions.TakinaException;

public interface UsuarioService {
    UsuarioDto getUsuarioId(Long cancionId) throws TakinaException;

    List<UsuarioDto> getUsuarios() throws TakinaException;

	// 
    UsuarioDto getUsuarioNombre(String nombre) throws TakinaException;

	// US001 - Como internauta Quiero registrarme en la aplicación Para usar la Plataforma
    UsuarioDto createUsuario(CreateUsuarioDto createUsuarioDto) throws TakinaException;

}
