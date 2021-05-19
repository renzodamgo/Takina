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
@RequestMapping(path = "/takina"+"/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	// Mostrar todos los usuarios
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public TakinaResponse<List<UsuarioDto>> getUsuarios()
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.getUsuarios());
	}

	// Crear nuevo usuario
	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	public TakinaResponse<UsuarioDto> createUsuario(@RequestBody CreateUsuarioDto createUsuarioDto)
			throws TakinaException{
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.createUsuario(createUsuarioDto));
	}

	// Getter
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/id/{usuarioId}")
	public TakinaResponse<UsuarioDto> getUsuarioById(@PathVariable Long usuarioId) throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.getUsuarioId(usuarioId));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/nombre/{usuarioNombre}")
	public TakinaResponse<UsuarioDto> getUsuarioByNombre(@PathVariable String usuarioNombre) throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.getUsuarioNombre(usuarioNombre));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/apodo/{usuarioApodo}")
	public TakinaResponse<UsuarioDto> getUsuarioByApodo(@PathVariable String usuarioApodo) throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.getUsuarioApodo(usuarioApodo));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/correo/{usuarioCorreo}")
	public TakinaResponse<UsuarioDto> getUsuarioByCorreo(@PathVariable String usuarioCorreo) throws TakinaException {
		return new TakinaResponse<>( "Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.getUsuarioCorreo(usuarioCorreo));
	}

	// Busqueda de usuarios por Apodo
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("busqueda/nombre/{usuarioNombre}")
	public TakinaResponse<List<UsuarioDto>> getUsuariosByNombre(@PathVariable String usuarioNombre)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.getUsuariosByNombre(usuarioNombre));
	}

	// Funcion Login
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/login/{login}/{password}")
	public TakinaResponse<UsuarioDto> loginUsuarioByApodoOrCorreoUsingPassword(@PathVariable String login, @PathVariable String password)
			throws TakinaException{
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.loginUsuarioByApodoOrCorreoUsingPassword(login,password));
	}

	// Dar moderador de perfil de Músico a otro usuario
	
}
