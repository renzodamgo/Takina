package com.backend.services;

import java.util.List;

import com.backend.dtos.EventoDto;
import com.backend.dtos.creates.CreateEventoDto;
import com.backend.exceptions.TakinaException;

public interface EventoService {

    EventoDto getEventoId(Long eventoId) throws TakinaException;

    // (codigo) - Como usuario quiero ver todos lo eventos que hay Para decidir cual me interesa y poder asistir
    List<EventoDto> getEventos() throws TakinaException;

    // (codigo) - Como usuario Quiero buscar una canción para encontrar la canción que deseo escuchar en ese instante
    EventoDto getEventoNombre(String nombre) throws TakinaException;

    // (US016) - Como artista quiero publicar mis eventos y conciertos Para que mis seguidores esten al tanto de mis actividades
    EventoDto createEvento(CreateEventoDto createEventoDto) throws TakinaException;
    
}
