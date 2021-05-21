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
	@GetMapping("/busqueda/{proyectoNombre}")
	public TakinaResponse<List<ProyectoDto>> getProyectosByNombre(@PathVariable String proyectoNombre)
			throws TakinaException{
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
			proyectoService.getProyectosByNombre(proyectoNombre));
	}

	// Busqueda de proyectos por genero musical

}
