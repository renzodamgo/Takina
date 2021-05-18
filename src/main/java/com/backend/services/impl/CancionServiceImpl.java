package com.backend.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.backend.dtos.CancionDto;
import com.backend.dtos.creates.CreateCancionDto;
import com.backend.dtos.creates.CreateCancionSencilloDto;
import com.backend.entities.Cancion;
import com.backend.entities.ProyectoMusical;
import com.backend.entities.Artista;
import com.backend.exceptions.InternalServerErrorException;
import com.backend.exceptions.NotFoundException;
import com.backend.exceptions.TakinaException;
import com.backend.repositories.CancionRepository;
import com.backend.repositories.ProyectoMusicalRepository;
import com.backend.repositories.ArtistaRepository;
import com.backend.services.CancionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancionServiceImpl implements CancionService {
	@Autowired
	private ProyectoMusicalRepository proyectoMusicalRepository;

	@Autowired
	private ArtistaRepository artistaRepository;

    @Autowired
    private CancionRepository cancionRepository;
    private static final ModelMapper modelMapper = new ModelMapper();


    // -------------------------------------------------------
    @Override
    public CancionDto getCancionId(Long cancionId) throws TakinaException {
        return modelMapper.map(getCancionEntity(cancionId),CancionDto.class);

    }

    private Cancion getCancionEntity(Long cancionId) throws TakinaException {
        return cancionRepository.findById(cancionId)
                .orElseThrow(()-> new NotFoundException("NOTFOUND-404","CANCION_NOTFOUND-404"));
    }

    // -------------------------------------------------------
	@Override
    public List<CancionDto> getCanciones() throws TakinaException {
        List<Cancion> cancionesEntity = cancionRepository.findAll();
        return cancionesEntity.stream().map(cancion -> modelMapper.map(cancion, CancionDto.class)).collect(Collectors.toList());
    }

    // -------------------------------------------------------
    @Override
    public CancionDto getCancionNombre(String nombre) throws TakinaException {
        return modelMapper.map(getCancionEntityNombre(nombre),CancionDto.class);
    }

    private Cancion getCancionEntityNombre(String nombre) throws TakinaException {
        return cancionRepository.findByNombre(nombre)
                .orElseThrow(()-> new NotFoundException("NOTFOUND-404","CANCION_NOTFOUND-404"));
    }

    // --------------------------------------------------------
    @Transactional
    @Override
    public CancionDto createCancion(CreateCancionDto createCancionDto) throws TakinaException {
		ProyectoMusical proyectoId = proyectoMusicalRepository.findById(createCancionDto.getProyectoId())
				.orElseThrow(()->new NotFoundException("NOT-401-1","PROYECTO_NOT_FOUND"));

        Cancion cancion = new Cancion();
        cancion.setNombre(createCancionDto.getNombre());
        cancion.setAudio(createCancionDto.getAudio());
		cancion.setDuracion(createCancionDto.getDuracion());
		
		cancion.setFotoPortada(proyectoId.getFotoPortada());
		cancion.setLanzamiento(proyectoId.getLanzamiento());
        cancion.setGenero(proyectoId.getGenero());
        cancion.setProyecto(proyectoId);
        
        try {
            cancion = cancionRepository.save(cancion);
        }catch (Exception ex){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","CANCION_NOT_CREATED");
        }
        return modelMapper.map(getCancionEntity(cancion.getId()),CancionDto.class);
    }
	// --------------------------------------------------------
    @Transactional
    @Override
    public CancionDto createCancionSencillo(CreateCancionSencilloDto createCancionSencilloDto) throws TakinaException {
		Artista artista = artistaRepository.findById(createCancionSencilloDto.getArtistaId())
				.orElseThrow(()->new NotFoundException("NOT-401-1","ARTISTA_NOT_FOUND"));

		ProyectoMusical proyectoMusical = new ProyectoMusical();
		proyectoMusical.setNombre(createCancionSencilloDto.getNombre());
        proyectoMusical.setDuracion(createCancionSencilloDto.getDuracion());
		proyectoMusical.setTipoProyecto("Sencillo");
		proyectoMusical.setDescripcion(createCancionSencilloDto.getDescripcion());
		proyectoMusical.setLanzamiento(createCancionSencilloDto.getLanzamiento());
		proyectoMusical.setCanciones(1);
		proyectoMusical.setDiscografica(createCancionSencilloDto.getDiscografica());
		proyectoMusical.setArtista(artista);
		proyectoMusical.setFotoPortada(createCancionSencilloDto.getFotoPortada());
		proyectoMusical.setGenero(artista.getGenero());

		try {
			proyectoMusical = proyectoMusicalRepository.save(proyectoMusical);
        } catch (Exception ex){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","PROYECTO_NOT_CREATED");
        }

        Cancion cancion = new Cancion();
        cancion.setNombre(createCancionSencilloDto.getNombre());
        cancion.setAudio(createCancionSencilloDto.getAudio());
		cancion.setDuracion(createCancionSencilloDto.getDuracion());

		cancion.setFotoPortada(proyectoMusical.getFotoPortada());
		cancion.setLanzamiento(proyectoMusical.getLanzamiento());
        cancion.setGenero(proyectoMusical.getGenero());
        cancion.setProyecto(proyectoMusical);
        
        try {
            cancion = cancionRepository.save(cancion);	
        } catch (Exception ex){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","CANCION_NOT_CREATED");
        }

        return modelMapper.map(getCancionEntity(cancion.getId()),CancionDto.class);
    }
	
	// --------------------------------------------------------
	// Buscar por nombre
	@Override
    public List<CancionDto> getCancionesByNombre(String nombre) throws TakinaException {
        List<Cancion> results = cancionRepository.findByNombreContainingIgnoreCase(nombre);
        return results.stream().map(cancion -> modelMapper.map(cancion,CancionDto.class)).collect(Collectors.toList());
    }
	// Buscar por genero musical
	@Override
	public List<CancionDto> getCancionesByGeneroMusical(String generoMusical) throws TakinaException {
		List<Cancion> results = cancionRepository.findByGeneroContainingIgnoreCase(generoMusical);
        return results.stream().map(cancion -> modelMapper.map(cancion,CancionDto.class)).collect(Collectors.toList());
	}
}
