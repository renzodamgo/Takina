package com.backend.controllers;

import java.util.List;

import com.backend.dtos.LoginDto;
import com.backend.dtos.UsuarioDto;
import com.backend.dtos.creates.CreateUsuarioDto;
import com.backend.dtos.edits.EditUsuarioDto;
import com.backend.exceptions.TakinaException;
import com.backend.responses.TakinaResponse;
import com.backend.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/takina"+"/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public TakinaResponse<List<UsuarioDto>> getUsuarios()
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.getUsuarios());
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	public TakinaResponse<UsuarioDto> createUsuario(@RequestBody CreateUsuarioDto createUsuarioDto)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.createUsuario(createUsuarioDto));
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping
	public TakinaResponse<UsuarioDto> editUsuario(@RequestBody EditUsuarioDto editUsuarioDto)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.editUsuario(editUsuarioDto));
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/id/{usuarioId}")
	public TakinaResponse<String> deleteUser(@PathVariable Long usuarioId)
			throws TakinaException {
		usuarioService.deleteUser(usuarioId);
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				"Usuario eliminado.");
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/id/{usuarioId}")
	public TakinaResponse<UsuarioDto> getUsuarioById(@PathVariable Long usuarioId)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.getUsuarioId(usuarioId));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/nombre/{usuarioNombre}")
	public TakinaResponse<List<UsuarioDto>> getUsuariosByNombre(@PathVariable String usuarioNombre)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.getUsuariosByNombre(usuarioNombre));
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/login")
	public TakinaResponse<UsuarioDto> loginUsuarioByApodoOrCorreoUsingPassword(@RequestBody LoginDto loginDto)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.loginUsuarioByApodoOrCorreoUsingPassword(loginDto));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/premium/{usuarioId}")
	public TakinaResponse<UsuarioDto> togglePremium(@PathVariable Long usuarioId)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				usuarioService.togglePremium(usuarioId));
	}
}
