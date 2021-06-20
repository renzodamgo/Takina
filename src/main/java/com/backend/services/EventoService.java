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

	EventoDto getEventoId(Long eventoId) throws TakinaException;

	EventoDto getEventoNombre(String nombre) throws TakinaException;

	List<EventoDto> getEventos() throws TakinaException;

	EventoDto createEvento(CreateEventoDto createEventoDto) throws TakinaException;

	List<EventoDto> getEventosByDepartamento(String departamento) throws TakinaException;

	List<EventoDto> getEventosByNombre(String departamento) throws TakinaException;

	InvitadoDto createInvitado(CreateInvitadoDto createInvitadoDto) throws TakinaException;

	void deleteInvitado(Long eventoId, Long artistaId) throws TakinaException;

	InvitadosDto getInvitadosByEventoId(Long eventoId) throws TakinaException;

	AsistenteDto createAsistente(Long usuarioId, Long eventoId) throws TakinaException;

	void deleteAsistente(Long usuarioId, Long artistaId) throws TakinaException;
}
