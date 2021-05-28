package com.backend.controllers;

import com.backend.dtos.CancionDto;
import com.backend.dtos.creates.CreateCancionDto;
import com.backend.dtos.creates.CreateCancionProyectoDto;
import com.backend.dtos.creates.CreateCreditoDto;
import com.backend.exceptions.TakinaException;
import com.backend.responses.TakinaResponse;
import com.backend.services.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/takina"+"/canciones")
public class CancionController {
	@Autowired
	private CancionService cancionService;

	// Crear cancion
	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	public TakinaResponse<CancionDto> createCancion(@RequestBody CreateCancionDto createCancionDto)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				cancionService.createCancion(createCancionDto));
	}

	// Mostrar todas las canciones
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public TakinaResponse<List<CancionDto>> getCanciones()
			throws TakinaException {
		return new TakinaResponse<>( "Success",String.valueOf(HttpStatus.OK),"OK",
				cancionService.getCanciones());
	}

	// Crear cancion y proyecto musical sencillo
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/sencillo")
	public TakinaResponse<CancionDto> createCancionProyecto(@RequestBody CreateCancionProyectoDto createCancionSencilloDto)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				cancionService.createCancionProyecto(createCancionSencilloDto));
	}

	// Obtener Cancion por ID
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/id/{cancionId}")
	public TakinaResponse<CancionDto> getCancionById(@PathVariable Long cancionId)
			throws TakinaException {
		return new TakinaResponse<>( "Success",String.valueOf(HttpStatus.OK),"OK",
				cancionService.getCancionId(cancionId));
	}

	// Eliminar Cancion por ID
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/id/{cancionId}")
	public TakinaResponse<String> deleteCancionById(@PathVariable Long cancionId)
			throws TakinaException {
		cancionService.deleteCancionById(cancionId);
		return new TakinaResponse<>( "Success",String.valueOf(HttpStatus.OK),"OK",
				"Cancion Eliminada correctamente.");

	}

	// Busqueda por nombre
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/nombre/{cancionNombre}")
	public  TakinaResponse<List<CancionDto>> getCancionesByNombre(@PathVariable String cancionNombre)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				cancionService.getCancionesByNombre(cancionNombre));
	}

	// Busqueda por genero
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/genero/{generoMusical}")
	public  TakinaResponse<List<CancionDto>> getCancionesByGeneroMusical(@PathVariable String generoMusical)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				cancionService.getCancionesByGeneroMusical(generoMusical));
	}

	// Añadir credito a canción
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/credito")
	public  TakinaResponse<CancionDto> createCredito(@RequestBody CreateCreditoDto createCreditoDto)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				cancionService.createCredito(createCreditoDto));
	}

}
