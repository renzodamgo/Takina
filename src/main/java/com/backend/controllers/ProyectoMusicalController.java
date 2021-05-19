package com.backend.controllers;

import com.backend.dtos.ProyectoMusicalDto;
import com.backend.dtos.creates.CreateProyectoMusicalDto;
import com.backend.exceptions.TakinaException;
import com.backend.responses.TakinaResponse;
import com.backend.services.ProyectoMusicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/takina"+"/proyectos")
public class ProyectoMusicalController {
	@Autowired
	private ProyectoMusicalService proyectoMusicalService;
	
	// Crear proyecto
	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	public TakinaResponse<ProyectoMusicalDto> createProyectoMusical(@RequestBody CreateProyectoMusicalDto createProyectoMusicalDto) throws TakinaException{
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				proyectoMusicalService.createProyectoMusical(createProyectoMusicalDto));
	}

	// Obtener todos los proyectos
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public TakinaResponse<List<ProyectoMusicalDto>> getProyectosMusicales()
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
			proyectoMusicalService.getProyectosMusicales());
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/id/{proyectoId}")
	public TakinaResponse<ProyectoMusicalDto> getProyectoMusicalById(@PathVariable Long proyectoId)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
			proyectoMusicalService.getProyectoMusicalById(proyectoId));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/busqueda/nombre/{proyectoMusicalNombre}")
	public TakinaResponse<List<ProyectoMusicalDto>> getProyectosMusicalesByNombre(@PathVariable String proyectoMusicalNombre)
			throws TakinaException{
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
			proyectoMusicalService.getProyectosMusicalesByNombre(proyectoMusicalNombre));
	}

}
