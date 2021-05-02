package com.backend.services;

import java.util.List;

import com.backend.dtos.EventoDto;
import com.backend.dtos.creates.CreateEventoDto;
import com.backend.exceptions.TakinaException;

public interface EventoService {
	// Obtener Evento por ID
    EventoDto getEventoId(Long eventoId) throws TakinaException;
	// Obtener Evento por Nombre
	EventoDto getEventoNombre(String nombre) throws TakinaException;
	// Mostrar todos los eventos
	List<EventoDto> getEventos() throws TakinaException;

    // US024 - Como usuario Quiero ver todos lo eventos que hay cerca de mi Para decidir cual me interesa y asistir
    List<EventoDto> getEventosByDepartamento(String departamento) throws TakinaException;

    // US016 - Como artista quiero publicar un eventos y conciertos Para que mis seguidores esten al tanto de mis actividades
    EventoDto createEvento(CreateEventoDto createEventoDto) throws TakinaException;
    
}
