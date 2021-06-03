package com.takina.userservice.controller;

import com.takina.userservice.dto.UsuarioDto;
import com.takina.userservice.dto.create.CreateUsuarioDto;
import com.takina.userservice.dto.edit.EditUsuarioDto;
import com.takina.userservice.exceptions.UserNotFoundException;
import com.takina.userservice.responses.TakinaResponse;
import com.takina.userservice.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/takina"+"/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    // Mostrar todos los usuarios
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public TakinaResponse<List<UsuarioDto>> getUsuarios()
            throws UserNotFoundException {
        return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                usuarioService.getUsuarios());
    }

    // Crear nuevo usuario
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public TakinaResponse<UsuarioDto> createUsuario(@RequestBody CreateUsuarioDto createUsuarioDto)
            throws UserNotFoundException {
        return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                usuarioService.createUsuario(createUsuarioDto));
    }

    // Editar un usuario
    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public TakinaResponse<UsuarioDto> editUsuario(@RequestBody EditUsuarioDto editUsuarioDto)
            throws UserNotFoundException {
        return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                usuarioService.editUsuario(editUsuarioDto));
    }

    // Eliminar un usuario
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/id/{usuarioId}")
    public TakinaResponse<String> deleteUser(@PathVariable Long usuarioId)
            throws UserNotFoundException {
        usuarioService.deleteUser(usuarioId);
        return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                "Usuario eliminado.");
    }

    // Obtener por ID
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/id/{usuarioId}")
    public TakinaResponse<UsuarioDto> getUsuarioById(@PathVariable Long usuarioId)
            throws UserNotFoundException {
        return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                usuarioService.getUsuarioId(usuarioId));
    }

    // Busqueda de usuarios por Apodo
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/nombre/{usuarioNombre}")
    public TakinaResponse<List<UsuarioDto>> getUsuariosByNombre(@PathVariable String usuarioNombre)
            throws UserNotFoundException {
        return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                usuarioService.getUsuariosByNombre(usuarioNombre));
    }

    // Funcion Login
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/login/{login}/{password}")
    public TakinaResponse<UsuarioDto> loginUsuarioByApodoOrCorreoUsingPassword(@PathVariable String login, @PathVariable String password)
            throws UserNotFoundException {
        return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                usuarioService.loginUsuarioByApodoOrCorreoUsingPassword(login,password));
    }

    // Cambiar valor de premium
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/premium/{usuarioId}")
    public TakinaResponse<UsuarioDto> togglePremium(@PathVariable Long usuarioId)
            throws UserNotFoundException {
        return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                usuarioService.togglePremium(usuarioId));
    }

}

