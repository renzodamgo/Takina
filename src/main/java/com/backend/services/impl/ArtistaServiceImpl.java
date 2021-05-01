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
import org.springframework.stereotype.Service;

@Service
public class ArtistaServiceImpl implements ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;
    private static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public ArtistaDto getArtista(Long artistaId) throws TakinaException{
        return modelMapper.map(getArtistaEntity(artistaId), ArtistaDto.class);
    }

    private Artista getArtistaEntity(Long artistaId) throws TakinaException {
        return artistaRepository.findById(artistaId)
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
		artista.setNombre(createArtistaDto.getNombre());
		artista.setBiografia(createArtistaDto.getBiografia());
        artista.setFotoPerfil(createArtistaDto.getFotoPerfil());
		artista.setFotoPortada(createArtistaDto.getFotoPortada());
        artista.setDepartamentoOrigen(createArtistaDto.getDepartamentoOrigen());
		artista.setGeneroMusical(createArtistaDto.getGeneroMusical());

        try {
            artista = artistaRepository.save(artista);
        }catch (Exception ex){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","ARTISTA_NOT_CREATED");
        }
        return modelMapper.map(getArtistaEntity(artista.getId()),ArtistaDto.class);

    }
    //Buscar Artidtar por nombre
    @Override
    public List<ArtistaDto> getArtistasByName(String nombre) throws TakinaException{
        List<Artista> results = artistaRepository.findByNombreContainingIgnoreCase(nombre);
        return results.stream().map(artista -> modelMapper.map(artista,ArtistaDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<ArtistaDto> getArtistasByGeneroMusical(String genero_musical) throws TakinaException{
        List<Artista> results = artistaRepository.findByGeneroMusicalContainingIgnoreCase(genero_musical);
        return results.stream().map(artista -> modelMapper.map(artista,ArtistaDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<ArtistaDto> getArtistasByDepartamento(String departamento_origen) throws TakinaException{
        List<Artista> results = artistaRepository.findByDepartamentoOrigenContainingIgnoreCase(departamento_origen);
        return results.stream().map(artista -> modelMapper.map(artista,ArtistaDto.class)).collect(Collectors.toList());
    }

}
