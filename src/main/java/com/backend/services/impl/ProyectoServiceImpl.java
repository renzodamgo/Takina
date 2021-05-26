package com.backend.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.backend.dtos.ProyectoDto;
import com.backend.dtos.creates.CreateProyectoDto;
import com.backend.entities.Proyecto;
import com.backend.entities.Artista;
import com.backend.exceptions.InternalServerErrorException;
import com.backend.exceptions.NotFoundException;
import com.backend.exceptions.TakinaException;
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
		if (proyectoEntities.isEmpty()) {
			throw new NotFoundException("NOTFOUND-404", "CANCION_NOTFOUND-404");
		}
		return proyectoEntities.stream().map(proyecto -> modelMapper.map(proyecto, ProyectoDto.class)).collect(Collectors.toList());
	}

	// -------------------------------------------------------
	@Override
	public ProyectoDto getProyectoById(Long proyectoId) throws TakinaException {
		return modelMapper.map(getProyectoEntity(proyectoId), ProyectoDto.class);

	}

	private Proyecto getProyectoEntity(Long proyectoId) throws TakinaException {
		return proyectoRepository.findById(proyectoId)
				.orElseThrow(() -> new NotFoundException("NOTFOUND-404", "PROYECTO_NOTFOUND-404"));
	}
	// -------------------------------------------------------
	@Override
	public ProyectoDto getProyectoByNombre(String nombre) throws TakinaException {
		return modelMapper.map(getProyectoEntityNombre(nombre),ProyectoDto.class);
	}
	private Proyecto getProyectoEntityNombre(String nombre) throws TakinaException {
		return proyectoRepository.findByNombre(nombre)
				.orElseThrow(()-> new NotFoundException("NOTFOUND-404","PROYECTO_NOTFOUND-404"));
	}
	// --------------------------------------------------------


	@Transactional
	@Override
	public ProyectoDto createProyecto(CreateProyectoDto createproyectoDto) throws TakinaException {
		Artista artista = artistaRepository.findById(createproyectoDto.getArtistaId())
				.orElseThrow(() -> new NotFoundException("NOT-401-1", "ARTISTA_NOT_FOUND"));

//		if (proyectoRepository.findByNombre(createproyectoDto.getNombre())==createproyectoDto.getNombre())
		List<ProyectoDto> proyectoEntities = getProyectosByArtistaId(createproyectoDto.getArtistaId());

		for (ProyectoDto p : proyectoEntities) {
			if (p.getNombre().equals(createproyectoDto.getNombre())) {
				throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "PROYECTO_MUST_HAVE_DIFFERENT_NAME");
			}
		}

		Proyecto proyecto = new Proyecto();
		proyecto.setNombre(createproyectoDto.getNombre());
		proyecto.setTipo(createproyectoDto.getTipo());
		proyecto.setDescripcion(createproyectoDto.getDescripcion());
		proyecto.setLanzamiento(createproyectoDto.getLanzamiento());
		proyecto.setDiscografica(createproyectoDto.getDiscografica());
		proyecto.setArtista(artista);
		proyecto.setFotoPortada(createproyectoDto.getFotoPortada());
		proyecto.setGenero(artista.getGenero());

		try {
			proyecto = proyectoRepository.save(proyecto);
		} catch (Exception ex){
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","PROYECTO_NOT_CREATED");
		}

		return modelMapper.map(getProyectoEntity(proyecto.getId()),ProyectoDto.class);
	}

	public ProyectoDto replaceDescription(ProyectoDto proyectoDto)throws TakinaException {
		Proyecto proyecto = proyectoRepository.findById(proyectoDto.getId())
				.orElseThrow(()-> new NotFoundException("NOTFOUND-404","PROYECTO_NOTFOUND-404"));

		proyecto.setDescripcion(proyectoDto.getDescripcion());

		try {
			proyecto = proyectoRepository.save(proyecto);
		} catch (Exception ex){
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","PROYECTO_NOT_CREATED");
		}

		return modelMapper.map(getProyectoEntity(proyecto.getId()),ProyectoDto.class);
	}

	@Override
	public List<ProyectoDto> getProyectosByNombre(String nombre) throws TakinaException{
		List<Proyecto> results = proyectoRepository.findByNombreContainingIgnoreCase(nombre);
		return results.stream().map(proyecto -> modelMapper.map(proyecto,ProyectoDto.class)).collect(Collectors.toList());
	}
}
