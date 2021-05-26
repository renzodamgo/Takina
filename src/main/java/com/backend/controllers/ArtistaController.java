package com.backend.controllers;

import com.backend.dtos.ArtistaDto;
import com.backend.dtos.creates.CreateArtistaDto;
import com.backend.dtos.edits.EditArtistaDto;
import com.backend.exceptions.TakinaException;
import com.backend.responses.TakinaResponse;
import com.backend.services.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/takina"+"/artistas")
public class ArtistaController {
	@Autowired
	private ArtistaService artistaService;

	// Crear Artista
	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	public TakinaResponse<ArtistaDto> createArtista(@RequestBody CreateArtistaDto createArtistaDto)
			throws TakinaException{
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
					artistaService.createArtista(createArtistaDto));
	}

	// editar artista
	@ResponseStatus(HttpStatus.OK)
	@PutMapping
	public TakinaResponse<ArtistaDto> editArtista(@RequestBody EditArtistaDto editArtistaDto) throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK), "OK", artistaService.editArtista(editArtistaDto));
	}

	// Mostrar todos los artistas
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public TakinaResponse<List<ArtistaDto>> getArtistas()
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
					artistaService.getArtistas());
	}

	// Getters
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/id/{artistaId}")
	public TakinaResponse<ArtistaDto> getArtistaById(@PathVariable Long artistaId)
			throws TakinaException {
		return new TakinaResponse<>( "Success",String.valueOf(HttpStatus.OK),"OK",
					artistaService.getArtista(artistaId));
	}

	// Busqueda por genero
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/genero/{artistaGenero}")
	public TakinaResponse<List<ArtistaDto>> getArtistasByGenero(@PathVariable String artistaGenero)
			throws TakinaException{
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
					artistaService.getArtistasByGenero(artistaGenero));
	}

	// Busqueda por departamento
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/departamento/{artistaDepartamento}")
	public TakinaResponse<List<ArtistaDto>> getArtistasByDepartamento(@PathVariable String artistaDepartamento)
			throws TakinaException{
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
					artistaService.getArtistasByDepartamento(artistaDepartamento));
	}

	// Busqueda por nombre
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/nombre/{artistaNombre}")
	public TakinaResponse<List<ArtistaDto>> searchArtistasByNombre(@PathVariable String artistaNombre)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
					artistaService.searchArtistasByNombre(artistaNombre));
	}

	// Otorgar permisos administrativos a otro usuario
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/administracion/{artistaid}/{usuarioId}/{nivel}")
	public TakinaResponse<ArtistaDto> giveAdministrador(@PathVariable Long artistaid, @PathVariable Long usuarioId, @PathVariable Integer nivel)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
					artistaService.giveAdministrador(artistaid,usuarioId,nivel));
	}

}