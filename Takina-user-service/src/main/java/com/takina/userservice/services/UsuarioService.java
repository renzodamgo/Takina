package com.takina.userservice.services;

import com.takina.userservice.dto.create.CreateUsuarioDto;
import com.takina.userservice.dto.UsuarioDto;
import com.takina.userservice.dto.edit.EditUsuarioDto;
import com.takina.userservice.exceptions.UserNotFoundException;

import java.util.List;

public interface UsuarioService {
    // Obtener todos los usuarios
    List<UsuarioDto> getUsuarios() throws UserNotFoundException;
    // Obtener usuario por ID
    UsuarioDto getUsuarioId(Long usuarioId) throws UserNotFoundException;
    // Obtener usuario por Apodo
    UsuarioDto getUsuarioApodo(String apodo) throws UserNotFoundException;
    // Obtener usuario por Correo
    UsuarioDto getUsuarioCorreo(String correo) throws UserNotFoundException;
    // Obtener usuario por Nombre
    UsuarioDto getUsuarioNombre(String nombre) throws UserNotFoundException;

    // US001 - Registro
    UsuarioDto createUsuario(CreateUsuarioDto createUsuarioDto) throws UserNotFoundException;

    // US002 - Login
    UsuarioDto loginUsuarioByApodoOrCorreoUsingPassword(String login, String password) throws UserNotFoundException;

    // US021 - Buscar otro usuario por su nombre
    List<UsuarioDto> getUsuariosByNombre(String nombre) throws UserNotFoundException;

    // Crear Reproduccion
    //ReproduccionDto createReproduccion(Long usuarioId, Long cancionId) throws TakinaException;

    // US030 - Historial de reproducciones
    //HistorialDto getHistorial(Long usuarioId) throws TakinaException;

    // US009 - Creacion de seguidor
    //SeguidorDto createSeguidor(Long usuarioId, Long artistaId) throws TakinaException;

    // US031 - Eliminacion de seguidor
    //void deleteSeguidor(Long usuarioId, Long artistaId) throws TakinaException;

    // US032 - Creacion de asistente
    //AsistenteDto createAsistente(Long usuarioId, Long eventoId) throws TakinaException;

    // US033 - Eliminacion de asistente
    //void deleteAsistente(Long usuarioId, Long artistaId) throws TakinaException;

    // US020 - Modificar foto de usuario (PUT de usuario)
    UsuarioDto editUsuario(EditUsuarioDto editUsuarioDto) throws UserNotFoundException;

    // US011 - Actualizacion a premium
    UsuarioDto togglePremium(Long usuarioId) throws UserNotFoundException;

    // US037 - eliminar usuario
    void deleteUser(Long usuarioId) throws UserNotFoundException;
}
