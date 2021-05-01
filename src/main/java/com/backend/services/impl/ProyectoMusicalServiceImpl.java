package com.backend.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.backend.dtos.ProyectoMusicalDto;
import com.backend.dtos.creates.CreateProyectoMusicalDto;
import com.backend.entities.ProyectoMusical;
import com.backend.exceptions.InternalServerErrorException;
import com.backend.exceptions.NotFoundException;
import com.backend.exceptions.TakinaException;
import com.backend.repositories.ProyectoMusicalRepository;
import com.backend.services.ProyectoMusicalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProyectoMusicalServiceImpl implements ProyectoMusicalService {


    @Autowired
    private ProyectoMusicalRepository ProyectoMusicalRepository;
    private static final ModelMapper modelMapper = new ModelMapper();

    // -------------------------------------------------------
    @Override
    public ProyectoMusicalDto getProyectoMusicalId(Long ProyectoMusicalId) throws TakinaException {
        return modelMapper.map(getProyectoMusicalEntity(ProyectoMusicalId),ProyectoMusicalDto.class);

    }

    private ProyectoMusical getProyectoMusicalEntity(Long ProyectoMusicalId) throws TakinaException {
        return ProyectoMusicalRepository.findById(ProyectoMusicalId)
                .orElseThrow(()-> new NotFoundException("NOTFOUND-404","ProyectoMusical_NOTFOUND-404"));
    }

    // -------------------------------------------------------
    @Override
    public List<ProyectoMusicalDto> getProyectosMusicales() throws TakinaException {
        List<ProyectoMusical> ProyectoMusicalesEntity = ProyectoMusicalRepository.findAll();
        return ProyectoMusicalesEntity.stream().map(ProyectoMusical -> modelMapper.map(ProyectoMusical, ProyectoMusicalDto.class)).collect(Collectors.toList());
    }

    // -------------------------------------------------------
    @Override
    public ProyectoMusicalDto getProyectoMusicalNombre(String nombre) throws TakinaException {
        return modelMapper.map(getProyectoMusicalEntityNombre(nombre),ProyectoMusicalDto.class);
    }

    private ProyectoMusical getProyectoMusicalEntityNombre(String nombre) throws TakinaException {
        return ProyectoMusicalRepository.findByNombre(nombre)
                .orElseThrow(()-> new NotFoundException("NOTFOUND-404","ProyectoMusical_NOTFOUND-404"));
    }

    // --------------------------------------------------------
    @Transactional
    @Override
    public ProyectoMusicalDto createProyectoMusical(CreateProyectoMusicalDto createProyectoMusicalDto) throws TakinaException {
        ProyectoMusical ProyectoMusical = new ProyectoMusical();
        ProyectoMusical.setNombre(createProyectoMusicalDto.getNombre());
        ProyectoMusical.setDuracion(createProyectoMusicalDto.getDuracion());
		ProyectoMusical.setTipoProyecto(createProyectoMusicalDto.getTipoProyecto());
		ProyectoMusical.setDescripcion(createProyectoMusicalDto.getDescripcion());
		ProyectoMusical.setLanzamiento(createProyectoMusicalDto.getLanzamiento());
		ProyectoMusical.setCanciones(createProyectoMusicalDto.getCanciones());
		ProyectoMusical.setDiscografica(createProyectoMusicalDto.getDiscografica());
		//ProyectoMusical.setArtista(createProyectoMusicalDto.getArtista_id());
		ProyectoMusical.setRutaImagen(createProyectoMusicalDto.getRutaImagen());

        try {
            ProyectoMusical = ProyectoMusicalRepository.save(ProyectoMusical);
        }catch (Exception ex){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","INTERNAL_SERVER_ERROR");
        }
        return modelMapper.map(getProyectoMusicalEntity(ProyectoMusical.getId()),ProyectoMusicalDto.class);
    }
}
