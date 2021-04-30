package com.backend.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.backend.dtos.ArtistaDto;
import com.backend.dtos.creates.CreateArtistaDto;
import com.backend.entities.Artista;
import com.backend.exceptions.InternalServerErrorException;
import com.backend.exceptions.NotFoundException;
import com.backend.exceptions.TakinaException;
import com.backend.repositories.ArtistaRepository;
import com.backend.services.ArtistaService;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ArtistaServiceImpl implements ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;
    private static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public ArtistaDto getArtista(Long Id) throws TakinaException{
        return modelMapper.map(getArtistaEntity(Id), ArtistaDto.class);
    }

    private Artista getArtistaEntity(Long ArtistaId) throws TakinaException {
        return artistaRepository.findById(ArtistaId)
                .orElseThrow(()-> new NotFoundException("NOTFOUND-404","ARTISTA_NOTFOUND-404"));
    }

    // --------------------------------------------------------

    public ArtistaDto getArtistaNombre(String nombre) throws TakinaException {
        return modelMapper.map(getArtistaEntityNombre(nombre), ArtistaDto.class);
    }

    private Object getArtistaEntityNombre(String nombre) throws NotFoundException {
        return artistaRepository.findByNombre(nombre).orElseThrow(()-> new NotFoundException("NOTFOUND-404","CANCION_NOTFOUND-404"));
    }

    // --------------------------------------------------------
    @Override
    public List<ArtistaDto> getArtistas() throws TakinaException {
        List<Artista> artistaEntity = artistaRepository.findAll();
        return artistaEntity.stream().map(artista -> modelMapper.map(artista, ArtistaDto.class)).collect(Collectors.toList());
    }

    // --------------------------------------------------------
    @Override
    public ArtistaDto createArtista(CreateArtistaDto createArtistaDto) throws TakinaException {
        Artista artista = new Artista();
        artista.setBiografia(createArtistaDto.getBiografia());
        artista.setDepartamento_origen(createArtistaDto.getDepartamento_origen());
        artista.setFoto(createArtistaDto.getFoto_perfil());
        artista.setGenero_musical(createArtistaDto.getGenero_musical());
        artista.setNombre(createArtistaDto.getNombre());
        artista.setOyentes_total(createArtistaDto.getOyentesTotal());
        artista.setDepartamento_origen(createArtistaDto.getDepartamento_origen());
        artista.setSeguidores(createArtistaDto.getSeguidores());

        try {
            artista = artistaRepository.save(artista);
        }catch (Exception ex){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","INTERNAL_SERVER_ERROR");
        }
        return modelMapper.map(getArtistaEntity(artista.getId()),ArtistaDto.class);

    }

}