package com.backend.controllers;

import java.util.List;

import com.backend.dtos.ProyectoDto;
import com.backend.dtos.ProyectoMiniDto;
import com.backend.dtos.creates.CreateProyectoDto;
import com.backend.dtos.edits.EditProyectoDto;
import com.backend.exceptions.TakinaException;
import com.backend.responses.TakinaResponse;
import com.backend.services.ProyectoService;

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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/takina"+"/proyectos")
public class ProyectoController {
	@Autowired
	private ProyectoService proyectoService;

	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	public TakinaResponse<ProyectoDto> createProyecto(@RequestBody CreateProyectoDto createProyectoDto) throws TakinaException{
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				proyectoService.createProyecto(createProyectoDto));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public TakinaResponse<List<ProyectoDto>> getProyectos()
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
			proyectoService.getProyectos());
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/id/{proyectoId}")
	public TakinaResponse<ProyectoDto> getProyectoById(@PathVariable Long proyectoId)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
			proyectoService.getProyectoById(proyectoId));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/ultimos")
	public TakinaResponse<List<ProyectoMiniDto>> getUltimos10ProyectosSubidos()
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
			proyectoService.getUltimos10ProyectosSubidos());
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/nombre/{proyectoNombre}")
	public TakinaResponse<List<ProyectoDto>> getProyectosByNombre(@PathVariable String proyectoNombre)
			throws TakinaException {
		return new TakinaResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
				proyectoService.getProyectosByNombre(proyectoNombre));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/artista/{artistaId}")
	public TakinaResponse<List<ProyectoDto>> getProyectosByArtistaId(@PathVariable Long artistaId)
			throws TakinaException {
		List<ProyectoDto> searchResult = proyectoService.getProyectosByArtistaId(artistaId);
		if (searchResult.isEmpty()) {
			return new TakinaResponse<>("Failure", String.valueOf(HttpStatus.NOT_FOUND),
					String.format("NO PROYECTOS WERE FOUND WITH ARTISTA ID: %d", artistaId));
		}
		return new TakinaResponse<>("Success", String.valueOf(HttpStatus.OK), "OK", searchResult);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/genero/{genero}")
	public TakinaResponse<List<ProyectoDto>> getProyectosByGenero(@PathVariable String genero)
			throws TakinaException {
		List<ProyectoDto> searchResult = proyectoService.getProyectosByGenero(genero);
		if (searchResult.isEmpty()) {
			return new TakinaResponse<>("Failure", String.valueOf(HttpStatus.NOT_FOUND),
					String.format("NO PROYECTOS WERE FOUND WITH GENERO: %s", genero));
		}
		return new TakinaResponse<>("Success", String.valueOf(HttpStatus.OK), "OK", searchResult);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping
	public TakinaResponse<ProyectoDto> editProyecto(@RequestBody EditProyectoDto editProyectoDto)
			throws TakinaException {
		return new TakinaResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
				proyectoService.editProyecto(editProyectoDto));
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/id/{proyectoId}")
	public TakinaResponse<String> deleteProyectoById(@PathVariable Long proyectoId)
			throws TakinaException {
		proyectoService.deleteProyectoById(proyectoId);
		return new TakinaResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
				"Proyecto eliminado.");
	}
}