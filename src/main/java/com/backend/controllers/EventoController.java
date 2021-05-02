package com.backend.controllers;

import java.util.List;

import com.backend.dtos.EventoDto;
import com.backend.dtos.creates.CreateEventoDto;
import com.backend.exceptions.TakinaException;
import com.backend.responses.TakinaResponse;
import com.backend.services.EventoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(path = "/takina"+"/v1")
public class EventoController {
    
    @Autowired
    private EventoService eventoService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/eventos/id/{eventoId}")
    public TakinaResponse<EventoDto> getEventoId(@PathVariable Long eventoId) throws TakinaException {
        return new TakinaResponse<>( "Success",String.valueOf(HttpStatus.OK),
                "OK",eventoService.getEventoId(eventoId));

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/eventos/todos")
    public TakinaResponse<List<EventoDto>> getEventos() throws TakinaException {
        return new TakinaResponse<>("Success", String.valueOf(HttpStatus.OK), "OK", eventoService.getEventos());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/eventos/nombre/{eventoNombre}")
    public TakinaResponse<EventoDto> getEventoNombre(@PathVariable String eventoNombre) throws TakinaException {
        return new TakinaResponse<>( "Success",String.valueOf(HttpStatus.OK),
                "OK",eventoService.getEventoNombre(eventoNombre));

    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/eventos")
    public TakinaResponse<EventoDto> createArtista(@RequestBody CreateEventoDto createEventoDto)
            throws TakinaException{
        return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                eventoService.createEvento(createEventoDto));
    }
}
