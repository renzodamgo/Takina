package com.backend.controllers;

import java.util.List;

import com.backend.dtos.CancionDto;
import com.backend.dtos.EstadisticaDto;
import com.backend.dtos.ReproduccionDto;
import com.backend.dtos.creates.CreateCancionDto;
import com.backend.dtos.creates.CreateCancionProyectoDto;
import com.backend.dtos.creates.CreateCreditoDto;
import com.backend.exceptions.TakinaException;
import com.backend.responses.TakinaResponse;
import com.backend.services.CancionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/takina"+"/canciones")
public class CancionController {
	@Autowired
	private CancionService cancionService;

	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	public TakinaResponse<CancionDto> createCancion(@RequestBody CreateCancionDto createCancionDto)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				cancionService.createCancion(createCancionDto));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public TakinaResponse<List<CancionDto>> getCanciones()
			throws TakinaException {
		return new TakinaResponse<>( "Success",String.valueOf(HttpStatus.OK),"OK",
				cancionService.getCanciones());
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/sencillo")
	public TakinaResponse<CancionDto> createCancionProyecto(@RequestBody CreateCancionProyectoDto createCancionSencilloDto)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				cancionService.createCancionProyecto(createCancionSencilloDto));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/id/{cancionId}")
	public TakinaResponse<CancionDto> getCancionById(@PathVariable Long cancionId)
			throws TakinaException {
		return new TakinaResponse<>( "Success",String.valueOf(HttpStatus.OK),"OK",
				cancionService.getCancionId(cancionId));
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/id/{cancionId}")
	public TakinaResponse<String> deleteCancionById(@PathVariable Long cancionId)
			throws TakinaException {
		cancionService.deleteCancionById(cancionId);
		return new TakinaResponse<>( "Success",String.valueOf(HttpStatus.OK),"OK",
				"Cancion Eliminada correctamente.");
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/nombre/{cancionNombre}")
	public  TakinaResponse<List<CancionDto>> getCancionesByNombre(@PathVariable String cancionNombre)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				cancionService.getCancionesByNombre(cancionNombre));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/genero/{generoMusical}")
	public  TakinaResponse<List<CancionDto>> getCancionesByGeneroMusical(@PathVariable String generoMusical)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				cancionService.getCancionesByGeneroMusical(generoMusical));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/proyecto/{proyectoId}")
	public TakinaResponse<List<CancionDto>> getCancionesByProyectoId(@PathVariable Long proyectoId)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				cancionService.getCancionesByProyectoId(proyectoId));
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/playlist/{playlistId}")
	public TakinaResponse<List<CancionDto>> getCancionesByPlaylistId(@PathVariable Long playlistId)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				cancionService.getCancionesByPlaylistId(playlistId));
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/credito")
	public  TakinaResponse<CancionDto> createCredito(@RequestBody CreateCreditoDto createCreditoDto)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				cancionService.createCredito(createCreditoDto));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/reproduccion/{usuarioId}/{cancionId}")
	public TakinaResponse<ReproduccionDto> createReproduccion(@PathVariable Long usuarioId, @PathVariable Long cancionId)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				cancionService.createReproduccion(usuarioId,cancionId));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/historial/{usuarioId}")
	public TakinaResponse<List<ReproduccionDto>> getHistorial(@PathVariable Long usuarioId)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				cancionService.getHistorial(usuarioId));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/reproducciones/{artistaId}/fecha/{fechaIndice}")
	public TakinaResponse<EstadisticaDto> getReproduccionesByArtistaIdAndDate(@PathVariable Long artistaId, @PathVariable Integer fechaIndice)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				cancionService.getReproduccionesByArtistaIdAndDate(artistaId,fechaIndice));
	}
}
