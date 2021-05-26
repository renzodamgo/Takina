package com.backend.controllers;

import java.util.List;

import com.backend.dtos.UsuarioDto;
import com.backend.dtos.ReproduccionDto;
import com.backend.dtos.HistorialDto;
import com.backend.dtos.SeguidorDto;
import com.backend.dtos.AsistenteDto;
import com.backend.dtos.creates.CreateUsuarioDto;
import com.backend.dtos.edits.EditUsuarioDto;
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
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.createUsuario(createUsuarioDto));
	}

	// Editar un usuario
	@ResponseStatus(HttpStatus.OK)
	@PutMapping
	public TakinaResponse<UsuarioDto> editUsuario(@RequestBody EditUsuarioDto editUsuarioDto)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.editUsuario(editUsuarioDto));
	}

	// Eliminar un usuario
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/id/{usuarioId}")
	public TakinaResponse<String> deleteUser(@PathVariable Long usuarioId)
			throws TakinaException {
		usuarioService.deleteUser(usuarioId);
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				"Usuario eliminado.");
	}

	// Obtener por ID
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/id/{usuarioId}")
	public TakinaResponse<UsuarioDto> getUsuarioById(@PathVariable Long usuarioId)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.getUsuarioId(usuarioId));
	}

	// Busqueda de usuarios por Apodo
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/nombre/{usuarioNombre}")
	public TakinaResponse<List<UsuarioDto>> getUsuariosByNombre(@PathVariable String usuarioNombre)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.getUsuariosByNombre(usuarioNombre));
	}

	// Funcion Login
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/login/{login}/{password}")
	public TakinaResponse<UsuarioDto> loginUsuarioByApodoOrCorreoUsingPassword(@PathVariable String login, @PathVariable String password)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.loginUsuarioByApodoOrCorreoUsingPassword(login,password));
	}

	// Registrar una reproduccion
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/reproduccion/{usuarioId}/{cancionId}")
	public TakinaResponse<ReproduccionDto> createReproduccion(@PathVariable Long usuarioId, @PathVariable Long cancionId)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.createReproduccion(usuarioId,cancionId));
	}

	// Revisar el historial de reproduccion de un usuario
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/historial/{usuarioId}")
	public TakinaResponse<HistorialDto> getHistorial(@PathVariable Long usuarioId)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.getHistorial(usuarioId));
	}

	// Registrar un seguidor
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/seguidor/{usuarioId}/{artistaId}")
	public TakinaResponse<SeguidorDto> createSeguidor(@PathVariable Long usuarioId, @PathVariable Long artistaId)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.createSeguidor(usuarioId, artistaId));
	}

	// Eliminar un seguidor
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/seguidor/{usuarioId}/{artistaId}")
	public TakinaResponse<String> deleteSeguidor(@PathVariable Long usuarioId, @PathVariable Long artistaId)
			throws TakinaException {
		usuarioService.deleteSeguidor(usuarioId, artistaId);
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK","Seguidor eliminado.");
	}

	// Registrar un asistente
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/asistente/{usuarioId}/{eventoId}")
	public TakinaResponse<AsistenteDto> createAsistente(@PathVariable Long usuarioId, @PathVariable Long eventoId)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.createAsistente(usuarioId, eventoId));
	}

	// Eliminar un asistente
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/asistente/{usuarioId}/{eventoId}")
	public TakinaResponse<String> deleteAsistente(@PathVariable Long usuarioId, @PathVariable Long eventoId)
			throws TakinaException {
		usuarioService.deleteAsistente(usuarioId, eventoId);
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK","Seguidor eliminado.");
	}

	// Cambiar valor de premium
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/premium/{usuarioId}")
	public TakinaResponse<UsuarioDto> togglePremium(@PathVariable Long usuarioId)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.togglePremium(usuarioId));
	}

}
