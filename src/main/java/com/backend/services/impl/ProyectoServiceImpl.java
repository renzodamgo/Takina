package com.backend.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.backend.dtos.ProyectoDto;
import com.backend.dtos.ProyectoMiniDto;
import com.backend.dtos.creates.CreateProyectoDto;
import com.backend.dtos.edits.EditProyectoDto;
import com.backend.entities.Proyecto;
import com.backend.entities.Artista;
import com.backend.exceptions.InternalServerErrorException;
import com.backend.exceptions.TakinaException;
import com.backend.exceptions.ArtistaNotFoundException;
import com.backend.exceptions.ProyectoNotFoundException;
import com.backend.exceptions.IncorrectProyectoException;
import com.backend.repositories.ProyectoRepository;
import com.backend.repositories.ArtistaRepository;
import com.backend.services.ProyectoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProyectoServiceImpl implements ProyectoService {
	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private ArtistaRepository artistaRepository;

	@Autowired
	private ProyectoRepository proyectoRepository;

	// -------------------------------------------------------
	@Override
	public List<ProyectoDto> getProyectos() throws TakinaException {
		List<Proyecto> proyectoEntity = proyectoRepository.findAll();
		return proyectoEntity.stream().map(proyecto -> modelMapper.map(proyecto, ProyectoDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<ProyectoDto> getProyectosByArtistaId(Long artistaId) throws TakinaException {
		List<Proyecto> proyectoEntities = proyectoRepository.findByArtistaId(artistaId);
		return proyectoEntities.stream().map(proyecto -> modelMapper.map(proyecto, ProyectoDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<ProyectoDto> getProyectosByGenero(String genero) throws TakinaException {
		List<Proyecto> proyectoEntities = proyectoRepository.findByGeneroContainingIgnoreCase(genero);
		return proyectoEntities.stream().map(proyecto -> modelMapper.map(proyecto, ProyectoDto.class)).collect(Collectors.toList());
	}

	// -------------------------------------------------------
	@Override
	public ProyectoDto getProyectoById(Long proyectoId) throws TakinaException {
		return modelMapper.map(getProyectoEntity(proyectoId), ProyectoDto.class);
	}

	private Proyecto getProyectoEntity(Long proyectoId) throws TakinaException {
		return proyectoRepository.findById(proyectoId)
				.orElseThrow(() -> new ProyectoNotFoundException("Proyecto not found."));
	}
	// -------------------------------------------------------
	@Override
	public ProyectoDto getProyectoByNombre(String nombre) throws TakinaException {
		return modelMapper.map(getProyectoEntityNombre(nombre),ProyectoDto.class);
	}

	private Proyecto getProyectoEntityNombre(String nombre) throws TakinaException {
		return proyectoRepository.findByNombre(nombre)
				.orElseThrow(()-> new ProyectoNotFoundException("Proyecto not found."));
	}
	// --------------------------------------------------------
	@Transactional
	@Override
	public ProyectoDto createProyecto(CreateProyectoDto createProyectoDto) throws TakinaException {
		Artista artista = artistaRepository.findById(createProyectoDto.getArtistaId())
				.orElseThrow(() -> new ArtistaNotFoundException("Artista not found."));

		// Validación: un artista no puede tener dos proyectos del mismo nombre
		List<ProyectoDto> proyectos = getProyectosByArtistaId(createProyectoDto.getArtistaId());
		for (ProyectoDto p : proyectos) {
			if (p.getNombre().equals(createProyectoDto.getNombre())) {
				throw new IncorrectProyectoException("Proyecto with same name already exists.");
			}
		}

		Proyecto proyecto = new Proyecto();
		proyecto.setNombre(createProyectoDto.getNombre());
		proyecto.setTipo(createProyectoDto.getTipo());
		proyecto.setDescripcion(createProyectoDto.getDescripcion());
		proyecto.setLanzamiento(createProyectoDto.getLanzamiento());
		proyecto.setDiscografica(createProyectoDto.getDiscografica());
		proyecto.setArtista(artista);
		proyecto.setFotoPortada(createProyectoDto.getFotoPortada());
		proyecto.setGenero(artista.getGenero());
		proyecto.setFecha(LocalDateTime.now());

		try {
			proyecto = proyectoRepository.save(proyecto);
		} catch (Exception ex){
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","PROYECTO_NOT_CREATED");
		}

		return modelMapper.map(getProyectoEntity(proyecto.getId()),ProyectoDto.class);
	}

	@Override
	public List<ProyectoDto> getProyectosByNombre(String nombre) throws TakinaException {
		List<Proyecto> results = proyectoRepository.findByNombreContainingIgnoreCase(nombre);
		return results.stream().map(proyecto -> modelMapper.map(proyecto,ProyectoDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<ProyectoMiniDto> getUltimos10ProyectosSubidos() throws TakinaException {
		List<Proyecto> top10 = proyectoRepository.findTop10OrderByFecha();
		return top10.stream().map(proyecto -> modelMapper.map(proyecto,ProyectoMiniDto.class)).collect(Collectors.toList());
	}

	@Override
	public ProyectoDto editProyecto(EditProyectoDto editProyectoDto) throws TakinaException {
		Proyecto proyecto = getProyectoEntity(editProyectoDto.getId());
		
		proyecto.setNombre(editProyectoDto.getNombre());
		proyecto.setDescripcion(editProyectoDto.getDescripcion());
		// deberias de poder editar el lanzamiento? no seria una fecha fija
		proyecto.setLanzamiento(editProyectoDto.getLanzamiento());
		proyecto.setDiscografica(editProyectoDto.getDiscografica());
		proyecto.setFotoPortada(editProyectoDto.getFotoPortada());
		proyecto.setGenero(editProyectoDto.getGenero());
		
		try {
			proyecto = proyectoRepository.save(proyecto);
		} catch (Exception ex){
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","PROYECTO_NOT_EDITED");
		}

		return modelMapper.map(getProyectoEntity(proyecto.getId()),ProyectoDto.class);
	}
}
