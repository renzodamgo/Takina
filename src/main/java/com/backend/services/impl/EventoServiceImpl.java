package com.backend.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.backend.dtos.EventoDto;
import com.backend.dtos.creates.CreateEventoDto;
import com.backend.entities.Evento;
import com.backend.exceptions.InternalServerErrorException;
import com.backend.exceptions.NotFoundException;
import com.backend.exceptions.TakinaException;
import com.backend.repositories.EventoRepository;
import com.backend.services.EventoService;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class EventoServiceImpl implements EventoService {

    @Autowired
    private EventoRepository eventoRepository;
    private static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public EventoDto getEventoId(Long eventoId) throws TakinaException {
        return modelMapper.map(getEventoEntity(eventoId), EventoDto.class);
    }


    private Object getEventoEntity(Long eventoId) throws NotFoundException {
        return eventoRepository.findById(eventoId).orElseThrow(()-> new NotFoundException("NOTFOUND-404","Usuario_NOTFOUND-404"));
    }


    // -------------------------------------------------------
    @Override
    public List<EventoDto> getEventos() throws TakinaException {
        List<Evento> eventoEntity = eventoRepository.findAll();
        return eventoEntity.stream().map(evento -> modelMapper.map(evento, EventoDto.class)).collect(Collectors.toList());
    }

    // -------------------------------------------------------
    @Override
    public EventoDto getEventoNombre(String nombre) throws TakinaException {
        return modelMapper.map(getEventoEntityNombre(nombre), EventoDto.class);
    }

    private Object getEventoEntityNombre(String nombre) throws NotFoundException {
        return eventoRepository.findByNombre(nombre).orElseThrow(()-> new NotFoundException("NOTFOUND-404","CANCION_NOTFOUND-404"));
    }


    // -------------------------------------------------------
    @Override
    public EventoDto createEvento(CreateEventoDto createEventoDto) throws TakinaException {

        Evento evento = new Evento();
        evento.setNombre(createEventoDto.getNombre());
        evento.setLugar(createEventoDto.getLugar());
        evento.setFecha(createEventoDto.getFecha());
        evento.setPrecio(createEventoDto.getPrecio());
        evento.setFotoPortada(createEventoDto.getFotoPortada());

        try {
            evento = eventoRepository.save(evento);
        }
        catch (Exception ex){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","CANCION_NOT_CREATED");
        }

        return modelMapper.map(getEventoEntity(evento.getId()),EventoDto.class);
        
    }
    
    
}
