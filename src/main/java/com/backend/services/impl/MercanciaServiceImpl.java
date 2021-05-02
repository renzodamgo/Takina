package com.backend.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.backend.dtos.MercanciaDto;
import com.backend.dtos.creates.CreateMercanciaDto;
import com.backend.entities.Artista;
import com.backend.entities.Mercancia;
import com.backend.exceptions.InternalServerErrorException;
import com.backend.exceptions.NotFoundException;
import com.backend.exceptions.TakinaException;
import com.backend.repositories.ArtistaRepository;
import com.backend.repositories.MercanciaRepository;
import com.backend.services.MercanciaService;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MercanciaServiceImpl implements MercanciaService {

    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private MercanciaRepository mercanciaRepository;
    private static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public MercanciaDto getMercanciaId(Long mercanciaId) throws TakinaException {
        return modelMapper.map(getMercanciaEntity(mercanciaId), MercanciaDto.class);     
    }

    private Object getMercanciaEntity(Long mercanciaId) throws NotFoundException {
        return mercanciaRepository.findById(mercanciaId)
                .orElseThrow(()-> new NotFoundException("NOTFOUND-404","Usuario_NOTFOUND-404"));
    }

    // ----------------------------------------------------------------
    @Override
    public List<MercanciaDto> getMercancias() throws TakinaException {
        List<Mercancia> mercanciaEntity = mercanciaRepository.findAll();
        return mercanciaEntity.stream().map(mercancia -> modelMapper.map(mercancia, MercanciaDto.class)).collect(Collectors.toList());
    }

    // ----------------------------------------------------------------
    @Transactional
    @Override
    public MercanciaDto createMercancia(CreateMercanciaDto createMercanciaDto) throws TakinaException {

        Artista artistaId = artistaRepository.findById(createMercanciaDto.getArtistaId()).orElseThrow(()->new NotFoundException("NOT-401-1","PROYECTO_NOT_FOUND"));

        Mercancia mercancia = new Mercancia();
        mercancia.setNombre(createMercanciaDto.getNombre());
        mercancia.setPrecio(createMercanciaDto.getPrecio());
        mercancia.setFoto(createMercanciaDto.getFoto());
		mercancia.setDescripcion(createMercanciaDto.getDescripcion());
        mercancia.setArtista(artistaId);

        try {
            mercancia = mercanciaRepository.save(mercancia);
        }catch (Exception ex){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","CANCION_NOT_CREATED");
        }
        return modelMapper.map(getMercanciaEntity(mercancia.getId()),MercanciaDto.class);
        
    }
    
}
