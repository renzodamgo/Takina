package com.backend.services;

import java.util.List;

import com.backend.dtos.LoginDto;
import com.backend.dtos.UsuarioDto;
import com.backend.dtos.creates.CreateUsuarioDto;
import com.backend.dtos.edits.EditUsuarioDto;
import com.backend.exceptions.TakinaException;

public interface UsuarioService {

	List<UsuarioDto> getUsuarios() throws TakinaException;

	UsuarioDto getUsuarioId(Long cancionId) throws TakinaException;

	UsuarioDto getUsuarioApodo(String apodo) throws TakinaException;

	UsuarioDto getUsuarioCorreo(String correo) throws TakinaException;

	UsuarioDto createUsuario(CreateUsuarioDto createUsuarioDto) throws TakinaException;

	UsuarioDto loginUsuarioByApodoOrCorreoUsingPassword(LoginDto loginDto) throws TakinaException;

	List<UsuarioDto> getUsuariosByNombre(String nombre) throws TakinaException;

	UsuarioDto editUsuario(EditUsuarioDto editUsuarioDto) throws TakinaException;

	UsuarioDto togglePremium(Long usuarioId) throws TakinaException;

	void deleteUser(Long usuarioId) throws TakinaException;
}