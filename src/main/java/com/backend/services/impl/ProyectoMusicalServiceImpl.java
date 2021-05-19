package com.backend.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.backend.dtos.ProyectoMusicalDto;
import com.backend.dtos.creates.CreateProyectoMusicalDto;
import com.backend.entities.ProyectoMusical;
import com.backend.entities.Artista;
import com.backend.exceptions.InternalServerErrorException;
import com.backend.exceptions.NotFoundException;
import com.backend.exceptions.TakinaException;
import com.backend.repositories.ProyectoMusicalRepository;
import com.backend.repositories.ArtistaRepository;
import com.backend.services.ProyectoMusicalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProyectoMusicalServiceImpl implements ProyectoMusicalService {
	@Autowired
	private ArtistaRepository artistaRepository;

    @Autowired
    private ProyectoMusicalRepository proyectoMusicalRepository;
    private static final ModelMapper modelMapper = new ModelMapper();

    // -------------------------------------------------------
    @Override
    public ProyectoMusicalDto getProyectoMusicalById(Long ProyectoMusicalId) throws TakinaException {
        return modelMapper.map(getProyectoMusicalEntity(ProyectoMusicalId),ProyectoMusicalDto.class);

    }

    private ProyectoMusical getProyectoMusicalEntity(Long ProyectoMusicalId) throws TakinaException {
        return proyectoMusicalRepository.findById(ProyectoMusicalId)
                .orElseThrow(()-> new NotFoundException("NOTFOUND-404","PROYECTO_NOTFOUND-404"));
    }

    // -------------------------------------------------------
    @Override
    public List<ProyectoMusicalDto> getProyectosMusicales() throws TakinaException {
        List<ProyectoMusical> proyectoMusicalesEntity = proyectoMusicalRepository.findAll();
        return proyectoMusicalesEntity.stream().map(ProyectoMusical -> modelMapper.map(ProyectoMusical, ProyectoMusicalDto.class)).collect(Collectors.toList());
    }

    // -------------------------------------------------------
    @Override
    public ProyectoMusicalDto getProyectoMusicalNombre(String nombre) throws TakinaException {
        return modelMapper.map(getProyectoMusicalEntityNombre(nombre),ProyectoMusicalDto.class);
    }

    private ProyectoMusical getProyectoMusicalEntityNombre(String nombre) throws TakinaException {
        return proyectoMusicalRepository.findByNombre(nombre)
                .orElseThrow(()-> new NotFoundException("NOTFOUND-404","PROYECTO_NOTFOUND-404"));
    }

    // --------------------------------------------------------
    @Transactional
    @Override
    public ProyectoMusicalDto createProyectoMusical(CreateProyectoMusicalDto createProyectoMusicalDto) throws TakinaException {
		Artista artista = artistaRepository.findById(createProyectoMusicalDto.getArtistaId())
				.orElseThrow(()->new NotFoundException("NOT-401-1","ARTISTA_NOT_FOUND"));

        ProyectoMusical proyectoMusical = new ProyectoMusical();
        proyectoMusical.setNombre(createProyectoMusicalDto.getNombre());
        proyectoMusical.setDuracion(createProyectoMusicalDto.getDuracion());
		proyectoMusical.setTipo(createProyectoMusicalDto.getTipo());
		proyectoMusical.setDescripcion(createProyectoMusicalDto.getDescripcion());
		proyectoMusical.setLanzamiento(createProyectoMusicalDto.getLanzamiento());
		proyectoMusical.setCanciones(createProyectoMusicalDto.getCanciones());
		proyectoMusical.setDiscografica(createProyectoMusicalDto.getDiscografica());
		proyectoMusical.setArtista(artista);
		proyectoMusical.setFotoPortada(createProyectoMusicalDto.getFotoPortada());
		proyectoMusical.setGenero(artista.getGenero());

        try {
            proyectoMusical = proyectoMusicalRepository.save(proyectoMusical);
        }catch (Exception ex){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","PROYECTO_NOT_CREATED");
        }

		artista.getProyectos().add(proyectoMusical);

        return modelMapper.map(getProyectoMusicalEntity(proyectoMusical.getId()),ProyectoMusicalDto.class);
    }

	@Override
    public List<ProyectoMusicalDto> getProyectosMusicalesByNombre(String nombre) throws TakinaException{
        List<ProyectoMusical> results = proyectoMusicalRepository.findByNombreContainingIgnoreCase(nombre);
        return results.stream().map(proyectoMusical -> modelMapper.map(proyectoMusical,ProyectoMusicalDto.class)).collect(Collectors.toList());
    }
}
