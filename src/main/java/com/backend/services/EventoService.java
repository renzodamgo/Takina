package com.backend.services;

import java.util.List;

import com.backend.dtos.AsistenteDto;
import com.backend.dtos.EventoDto;
import com.backend.dtos.InvitadoDto;
import com.backend.dtos.InvitadosDto;
import com.backend.dtos.creates.CreateEventoDto;
import com.backend.dtos.creates.CreateInvitadoDto;
import com.backend.exceptions.TakinaException;

public interface EventoService {
	AsistenteDto createAsistente(Long usuarioId, Long eventoId) throws TakinaException;
	EventoDto createEvento(CreateEventoDto createEventoDto) throws TakinaException;
	EventoDto getEventoId(Long eventoId) throws TakinaException;
	EventoDto getEventoNombre(String nombre) throws TakinaException;
	InvitadoDto createInvitado(CreateInvitadoDto createInvitadoDto) throws TakinaException;
	InvitadosDto getInvitadosByEventoId(Long eventoId) throws TakinaException;
	List<EventoDto> getEventos() throws TakinaException;
	List<EventoDto> getEventosByDepartamento(String departamento) throws TakinaException;
	List<EventoDto> getEventosByNombre(String departamento) throws TakinaException;
	void deleteAsistente(Long usuarioId, Long artistaId) throws TakinaException;
	void deleteInvitado(Long eventoId, Long artistaId) throws TakinaException;
}
