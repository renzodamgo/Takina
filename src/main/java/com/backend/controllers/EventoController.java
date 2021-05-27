package com.backend.controllers;

import java.util.List;

import com.backend.dtos.EventoDto;
import com.backend.dtos.InvitadoDto;
import com.backend.dtos.InvitadosDto;
import com.backend.dtos.creates.CreateEventoDto;
import com.backend.dtos.creates.CreateInvitadoDto;
import com.backend.exceptions.TakinaException;
import com.backend.responses.TakinaResponse;
import com.backend.services.EventoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(path = "/takina"+"/eventos")
public class EventoController {
	@Autowired
	private EventoService eventoService;

	// Mostrar todos los eventos
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public TakinaResponse<List<EventoDto>> getEventos()
			throws TakinaException {
		return new TakinaResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
				eventoService.getEventos());
	}

	// Crear evento
	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	public TakinaResponse<EventoDto> createEvento(@RequestBody CreateEventoDto createEventoDto)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				eventoService.createEvento(createEventoDto));
	}

	// Obtener evento por ID
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/id/{eventoId}")
	public TakinaResponse<EventoDto> getEventoId(@PathVariable Long eventoId)
			throws TakinaException {
		return new TakinaResponse<>( "Success",String.valueOf(HttpStatus.OK),"OK",
				eventoService.getEventoId(eventoId));
	}

	// Busqueda por nombre
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/nombre/{eventoNombre}")
	public  TakinaResponse<List<EventoDto>> getEventosByNombre(@PathVariable String eventoNombre)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				eventoService.getEventosByNombre(eventoNombre));
	}

	// Busqueda por departamento
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/departamento/{eventoDepartamento}")
	public  TakinaResponse<List<EventoDto>> getEventosByDepartamento(@PathVariable String eventoDepartamento)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				eventoService.getEventosByDepartamento(eventoDepartamento));
	}

	// Creacion de invitado
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/invitados")
	public  TakinaResponse<InvitadoDto> createInvitado(@RequestBody CreateInvitadoDto createInvitadoDto)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				eventoService.createInvitado(createInvitadoDto));
	}

	// Get invitados por eventoId ordenado cronologicamente
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/invitados/{eventoId}")
	public  TakinaResponse<InvitadosDto> getInvitadosByEventoId(@PathVariable Long eventoId)
			throws TakinaException {
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				eventoService.getInvitadosByEventoId(eventoId));
	}
	

	// Eliminar invitado
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/invitados/{eventoId}/{artistaId}")
	public  TakinaResponse<String> deleteInvitado(@PathVariable Long eventoId, @PathVariable Long artistaId)
			throws TakinaException {
		eventoService.deleteInvitado(eventoId, artistaId);
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				"Invitado eliminado satisfactoriamente.");
	}


	
}
