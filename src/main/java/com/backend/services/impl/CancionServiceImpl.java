package com.backend.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.backend.dtos.CancionDto;
import com.backend.dtos.creates.CreateCancionDto;
import com.backend.entities.Cancion;
import com.backend.entities.ProyectoMusical;
import com.backend.exceptions.InternalServerErrorException;
import com.backend.exceptions.NotFoundException;
import com.backend.exceptions.TakinaException;
import com.backend.repositories.CancionRepository;
import com.backend.repositories.ProyectoMusicalRepository;
import com.backend.services.CancionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancionServiceImpl implements CancionService {
	@Autowired
	private ProyectoMusicalRepository proyectoMusicalRepository;

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
		ProyectoMusical proyectoId = proyectoMusicalRepository.findById(createCancionDto.getProyecto_id())
				.orElseThrow(()->new NotFoundException("NOT-401-1","PROYECTO_NOT_FOUND"));

        Cancion cancion = new Cancion();
        cancion.setNombre(createCancionDto.getNombre());
		cancion.setDuracion(createCancionDto.getDuracion());
        cancion.setAudio(createCancionDto.getAudio());
		cancion.setImagen(createCancionDto.getImagen());
		cancion.setLanzamiento(createCancionDto.getLanzamiento());
        cancion.setGenero_musical(createCancionDto.getGenero_musical());
        cancion.setProyecto(proyectoId);
        
        try {
            cancion = cancionRepository.save(cancion);
        }catch (Exception ex){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","CANCION_NOT_CREATED");
        }
        return modelMapper.map(getCancionEntity(cancion.getId()),CancionDto.class);
    }
}
