package com.backend.controllers;

import java.util.List;

import com.backend.dtos.UsuarioDto;
import com.backend.dtos.creates.CreateUsuarioDto;
import com.backend.exceptions.TakinaException;
import com.backend.responses.TakinaResponse;
import com.backend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/takina"+"/v1")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

	// Crear nuevo usuario
	@ResponseStatus(HttpStatus.OK)
    @PostMapping("/usuarios/nuevo")
    public TakinaResponse<UsuarioDto> createUsuario(@RequestBody CreateUsuarioDto createUsuarioDto)
            throws TakinaException{
        return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                usuarioService.createUsuario(createUsuarioDto));
    }

	// Mostrar todos los usuarios
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/usuarios/todos")
	public TakinaResponse<List<UsuarioDto>> getUsuarios()
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.getUsuarios());
	}

	// Getter
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/usuarios/id/{usuarioId}")
    public TakinaResponse<UsuarioDto> getUsuarioById(@PathVariable Long usuarioId) throws TakinaException {
        return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.getUsuarioId(usuarioId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/usuarios/nombre/{usuarioNombre}")
    public TakinaResponse<UsuarioDto> getUsuarioByNombre(@PathVariable String usuarioNombre) throws TakinaException {
        return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.getUsuarioNombre(usuarioNombre));
    }

	@ResponseStatus(HttpStatus.OK)
    @GetMapping("/usuarios/apodo/{usuarioApodo}")
    public TakinaResponse<UsuarioDto> getUsuarioByApodo(@PathVariable String usuarioApodo) throws TakinaException {
        return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.getUsuarioApodo(usuarioApodo));
    }

	@ResponseStatus(HttpStatus.OK)
    @GetMapping("/usuarios/correo/{usuarioCorreo}")
    public TakinaResponse<UsuarioDto> getUsuarioByCorreo(@PathVariable String usuarioCorreo) throws TakinaException {
        return new TakinaResponse<>( "Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.getUsuarioCorreo(usuarioCorreo));
    }

	// Busqueda de usuarios por Apodo
	@ResponseStatus(HttpStatus.OK)
    @GetMapping("/usuarios/buscar/nombre/{usuarioNombre}")
    public TakinaResponse<List<UsuarioDto>> getUsuariosByNombre(@PathVariable String usuarioNombre)
			throws TakinaException {
        return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.getUsuariosByNombre(usuarioNombre));
    }

	// Funcion Login
	@ResponseStatus(HttpStatus.OK)
    @GetMapping("/usuarios/login/{login}/{password}")
    public TakinaResponse<UsuarioDto> loginUsuarioByApodoOrCorreoUsingPassword(@PathVariable String login, @PathVariable String password)
            throws TakinaException{
        return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                usuarioService.loginUsuarioByApodoOrCorreoUsingPassword(login,password));
    }
}
