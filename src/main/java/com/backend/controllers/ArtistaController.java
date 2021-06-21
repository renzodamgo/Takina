package com.backend.controllers;

import java.util.List;

import com.backend.dtos.ArtistaDto;
import com.backend.dtos.EstadisticaDto;
import com.backend.dtos.SeguidorDto;
import com.backend.dtos.creates.CreateArtistaDto;
import com.backend.dtos.edits.EditArtistaDto;
import com.backend.exceptions.TakinaException;
import com.backend.responses.TakinaResponse;
import com.backend.services.ArtistaService;

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
@RequestMapping(path = "/takina"+"/artistas")
public class ArtistaController {
	@Autowired
	private ArtistaService artistaService;

	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	public TakinaResponse<ArtistaDto> createArtista(@RequestBody CreateArtistaDto createArtistaDto)
			throws TakinaException{
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
					artistaService.createArtista(createArtistaDto));
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping
	public TakinaResponse<ArtistaDto> editArtista(@RequestBody EditArtistaDto editArtistaDto) throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK), "OK", artistaService.editArtista(editArtistaDto));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public TakinaResponse<List<ArtistaDto>> getArtistas()
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
					artistaService.getArtistas());
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/id/{artistaId}")
	public TakinaResponse<ArtistaDto> getArtistaById(@PathVariable Long artistaId)
			throws TakinaException {
		return new TakinaResponse<>( "Success",String.valueOf(HttpStatus.OK),"OK",
					artistaService.getArtista(artistaId));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/genero/{artistaGenero}")
	public TakinaResponse<List<ArtistaDto>> getArtistasByGenero(@PathVariable String artistaGenero)
			throws TakinaException{
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
					artistaService.getArtistasByGenero(artistaGenero));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/departamento/{artistaDepartamento}")
	public TakinaResponse<List<ArtistaDto>> getArtistasByDepartamento(@PathVariable String artistaDepartamento)
			throws TakinaException{
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
					artistaService.getArtistasByDepartamento(artistaDepartamento));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/nombre/{artistaNombre}")
	public TakinaResponse<List<ArtistaDto>> getArtistasByNombre(@PathVariable String artistaNombre)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
					artistaService.getArtistasByNombre(artistaNombre));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/administracion/{artistaId}/{usuarioId}/{nivel}")
	public TakinaResponse<ArtistaDto> giveAdministrador(@PathVariable Long artistaId, @PathVariable Long usuarioId, @PathVariable Integer nivel)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
					artistaService.giveAdministrador(artistaId,usuarioId,nivel));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/estadistica/seguidores/{artistaId}/{indice}")
	public TakinaResponse<EstadisticaDto> getSeguidoresByIdAndDate(@PathVariable Long artistaId, @PathVariable Integer indice)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
					artistaService.getSeguidoresByIdAndDate(artistaId,indice));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/seguidor/{usuarioId}/{artistaId}")
	public TakinaResponse<SeguidorDto> createSeguidor(@PathVariable Long usuarioId, @PathVariable Long artistaId)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				artistaService.createSeguidor(usuarioId, artistaId));
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/seguidor/{usuarioId}/{artistaId}")
	public TakinaResponse<String> deleteSeguidor(@PathVariable Long usuarioId, @PathVariable Long artistaId)
			throws TakinaException {
		artistaService.deleteSeguidor(usuarioId, artistaId);
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK","Seguidor eliminado.");
	}
}