package com.backend.services;

import java.util.List;

import com.backend.dtos.EventoDto;
import com.backend.dtos.creates.CreateEventoDto;
import com.backend.dtos.InvitadoDto;
import com.backend.exceptions.TakinaException;

public interface EventoService {
	// Obtener Evento por ID
	EventoDto getEventoId(Long eventoId) throws TakinaException;
	// Obtener Evento por Nombre
	EventoDto getEventoNombre(String nombre) throws TakinaException;
	// Mostrar todos los eventos
	List<EventoDto> getEventos() throws TakinaException;

	// US016 -crear evento
	EventoDto createEvento(CreateEventoDto createEventoDto) throws TakinaException;

	// US024 - eventos por departamento
	List<EventoDto> getEventosByDepartamento(String departamento) throws TakinaException;

	// US036 - eventos por nombre
	List<EventoDto> getEventosByNombre(String departamento) throws TakinaException;

	// US034 - Creacion de invitado
	//InvitadoDto createInvitado(Long eventoId, Long artistaId) throws TakinaException;

	// US035 - Eliminacion de invitado
	//void deleteInvitado(Long eventoId, Long artistaId) throws TakinaException;
	
	// US027 - Artistas invitados por evento ID
	//List<InvitadoDto> getInvitadosByEventoId(Long eventoId) throws TakinaException;
}
