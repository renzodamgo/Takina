package com.backend.controllers;

import com.backend.dtos.ProyectoDto;
import com.backend.dtos.creates.CreateProyectoDto;
import com.backend.exceptions.TakinaException;
import com.backend.responses.TakinaResponse;
import com.backend.services.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/takina"+"/proyectos")
public class ProyectoController {
	@Autowired
	private ProyectoService proyectoService;

	// Crear proyecto
	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	public TakinaResponse<ProyectoDto> createProyecto(@RequestBody CreateProyectoDto createProyectoDto) throws TakinaException{
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				proyectoService.createProyecto(createProyectoDto));
	}

	// Obtener todos los proyectos
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public TakinaResponse<List<ProyectoDto>> getProyectos()
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
			proyectoService.getProyectos());
	}

	// Obtener por ID
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/id/{proyectoId}")
	public TakinaResponse<ProyectoDto> getProyectoById(@PathVariable Long proyectoId)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
			proyectoService.getProyectoById(proyectoId));
	}

	// Busqueda de proyectos por nombre
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/nombre/{proyectoNombre}")
	public TakinaResponse<List<ProyectoDto>> getProyectosByNombre(@PathVariable String proyectoNombre)
			throws TakinaException {
		return new TakinaResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
				proyectoService.getProyectosByNombre(proyectoNombre));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/artista_id/{artistaId}")
	public TakinaResponse<List<ProyectoDto>> getProyectoByArtistaId(@PathVariable Long artistaId)
			throws TakinaException {
		try {
			return new TakinaResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
					proyectoService.getProyectosByAritstaId(artistaId));
		} catch (Exception err) {
			return new TakinaResponse<>("Failure", String.valueOf(HttpStatus.NOT_FOUND), String.format("NO PROJECTS WHERE FOUND WITH ARTIST ID: %d", artistaId));
		}

	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping
	public TakinaResponse<ProyectoDto> updateDescription(@RequestBody ProyectoDto proyectoDto)
			throws TakinaException {
		return new TakinaResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
				proyectoService.replaceDescription(proyectoDto));
	}

}
