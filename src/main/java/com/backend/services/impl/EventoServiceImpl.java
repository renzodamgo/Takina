package com.backend.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.backend.dtos.AsistenteDto;
import com.backend.dtos.EventoDto;
import com.backend.dtos.InvitadoDto;
import com.backend.dtos.InvitadosDto;
import com.backend.dtos.creates.CreateEventoDto;
import com.backend.dtos.creates.CreateInvitadoDto;
import com.backend.entities.Artista;
import com.backend.entities.Asistente;
import com.backend.entities.Evento;
import com.backend.entities.Invitado;
import com.backend.entities.Usuario;
import com.backend.exceptions.InternalServerErrorException;
import com.backend.exceptions.NotFoundException;
import com.backend.exceptions.TakinaException;
import com.backend.repositories.ArtistaRepository;
import com.backend.repositories.AsistenteRepository;
import com.backend.repositories.EventoRepository;
import com.backend.repositories.InvitadoRepository;
import com.backend.repositories.UsuarioRepository;
import com.backend.services.EventoService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class EventoServiceImpl implements EventoService {
	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private ArtistaRepository artistaRepository;

	@Autowired
	private InvitadoRepository invitadoRepository;

	@Autowired
	private AsistenteRepository asistenteRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public EventoDto getEventoId(Long eventoId) throws TakinaException {
		return modelMapper.map(getEventoEntity(eventoId), EventoDto.class);
	}
	
	private Evento getEventoEntity(Long eventoId) throws NotFoundException {
		return eventoRepository.findById(eventoId)
				.orElseThrow(()-> new NotFoundException("NOTFOUND-404","Evento_NOTFOUND-404"));
	}

	@Override
	public List<EventoDto> getEventos() throws TakinaException {
		List<Evento> eventoEntity = eventoRepository.findAll();
		return eventoEntity.stream().map(evento -> modelMapper.map(evento, EventoDto.class)).collect(Collectors.toList());
	}

	@Override
	public EventoDto getEventoNombre(String nombre) throws TakinaException {
		return modelMapper.map(getEventoEntityNombre(nombre), EventoDto.class);
	}

	private Object getEventoEntityNombre(String nombre) throws NotFoundException {
		return eventoRepository.findByNombre(nombre).orElseThrow(()-> new NotFoundException("NOTFOUND-404","CANCION_NOTFOUND-404"));
	}

	@Override
	public EventoDto createEvento(CreateEventoDto createEventoDto) throws TakinaException {
		Evento evento = new Evento();
		evento.setNombre(createEventoDto.getNombre());
		evento.setLugar(createEventoDto.getLugar());
		evento.setDireccion(createEventoDto.getDireccion());
		evento.setFecha(createEventoDto.getFecha());
		evento.setPrecio(createEventoDto.getPrecio());
		evento.setFotoPortada(createEventoDto.getFotoPortada());
		evento.setDepartamento(createEventoDto.getDepartamento());
		evento.setDescripcion(createEventoDto.getDescripcion());

		try {
			evento = eventoRepository.save(evento);
		}
		catch (Exception ex){
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","CANCION_NOT_CREATED");
		}

		return modelMapper.map(getEventoEntity(evento.getId()),EventoDto.class);
		
	}
	
	@Override
	public List<EventoDto> getEventosByDepartamento(String departamento) throws TakinaException {
		List<Evento> results = eventoRepository.findByDepartamentoContainingIgnoreCase(departamento);
		return results.stream().map(evento -> modelMapper.map(evento,EventoDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<EventoDto> getEventosByNombre(String nombre) throws TakinaException {
		List<Evento> results = eventoRepository.findByNombreContainingIgnoreCase(nombre);
		return results.stream().map(evento -> modelMapper.map(evento,EventoDto.class)).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public InvitadoDto createInvitado(CreateInvitadoDto createInvitadoDto) throws TakinaException {
		Optional<Invitado> validacion = invitadoRepository.findByEventoIdAndArtistaId(
			createInvitadoDto.getEventoId(), createInvitadoDto.getArtistaId());
		
		if (validacion.isPresent()) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","INVITADO_NOT_CREATED");
		}

		Evento evento = getEventoEntity(createInvitadoDto.getEventoId());
		Artista artista = artistaRepository.findById(createInvitadoDto.getArtistaId())
				.orElseThrow(()->new NotFoundException("NOT-401-1","ARTISTA_NOT_FOUND"));

		Invitado invitado = new Invitado();
		invitado.setArtista(artista);
		invitado.setEvento(evento);
		invitado.setHoraInicio(createInvitadoDto.getHoraInicio());
		invitado.setHoraFin(createInvitadoDto.getHoraFin());

		try {
			invitado = invitadoRepository.save(invitado);
		} catch (Exception ex) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","INVITADO_NOT_FOUND");
		}

		return modelMapper.map(invitado,InvitadoDto.class);
	}

	@Override
	public void deleteInvitado(Long eventoId, Long artistaId) throws TakinaException {
		Optional<Invitado> validacion = invitadoRepository.findByEventoIdAndArtistaId(eventoId, artistaId);
		if (validacion.isPresent()) {
			invitadoRepository.deleteById(validacion.get().getId());
		} else {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","INVITADO_NOT_FOUND");
		}
	}

	@Override
	public InvitadosDto getInvitadosByEventoId(Long eventoId) throws TakinaException {
		Evento evento = getEventoEntity(eventoId);
		List<Invitado> resultado = invitadoRepository.findByEventoIdOrderByHoraInicio(eventoId);

		InvitadosDto invitados = new InvitadosDto();
		invitados.setNombre(evento.getNombre());
		invitados.setInvitados(resultado.stream()
										.map(invitado -> modelMapper.map(invitado,InvitadoDto.class))
										.collect(Collectors.toList()));

		return invitados;
	}	

	@Transactional
	@Override
	public AsistenteDto createAsistente(Long usuarioId, Long eventoId) throws TakinaException {
		Optional<Asistente> validacion = asistenteRepository.findByUsuarioIdAndEventoId(usuarioId, eventoId);
		
		if (validacion.isPresent()) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","SEGUIDOR_NOT_CREATED");
		}

		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(()->new NotFoundException("NOT-401-1","USUARIO_NOT_FOUND"));
		Evento evento = eventoRepository.findById(eventoId)
				.orElseThrow(()->new NotFoundException("NOT-401-1","ARTISTA_NOT_FOUND"));

		Asistente asistente = new Asistente();
		asistente.setUsuario(usuario);
		asistente.setEvento(evento);
		asistente.setFecha(LocalDateTime.now());

		try {
			asistente = asistenteRepository.save(asistente);
		} catch (Exception ex) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","SEGUIDOR_NOT_CREATED");
		}

		evento.setInteresados(evento.getInteresados()+1);

		return modelMapper.map(asistente,AsistenteDto.class);
	}

	@Override
	public void deleteAsistente(Long usuarioId, Long eventoId) throws TakinaException {
		Optional<Asistente> validacion = asistenteRepository.findByUsuarioIdAndEventoId(usuarioId, eventoId);
		if (validacion.isPresent()) {
			asistenteRepository.deleteById(validacion.get().getId());

			Evento evento = eventoRepository.findById(eventoId)
				.orElseThrow(()->new NotFoundException("NOT-401-1","EVENTO_NOT_FOUND"));
			evento.setInteresados(evento.getInteresados()-1);

		} else {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","SEGUIDOR_NOT_FOUND");
		}
	}
}
