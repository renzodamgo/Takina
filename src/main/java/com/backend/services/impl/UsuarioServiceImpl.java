package com.backend.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.backend.dtos.UsuarioDto;
import com.backend.dtos.creates.CreateUsuarioDto;
import com.backend.entities.Usuario;
import com.backend.exceptions.InternalServerErrorException;
import com.backend.exceptions.NotFoundException;
import com.backend.exceptions.TakinaException;
import com.backend.repositories.UsuarioRepository;
import com.backend.services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {


    @Autowired
    private UsuarioRepository UsuarioRepository;
    private static final ModelMapper modelMapper = new ModelMapper();

    // -------------------------------------------------------
    @Override
    public UsuarioDto getUsuarioId(Long UsuarioId) throws TakinaException {
        return modelMapper.map(getUsuarioEntity(UsuarioId),UsuarioDto.class);

    }

    private Usuario getUsuarioEntity(Long UsuarioId) throws TakinaException {
        return UsuarioRepository.findById(UsuarioId)
                .orElseThrow(()-> new NotFoundException("NOTFOUND-404","Usuario_NOTFOUND-404"));
    }

    // -------------------------------------------------------
    @Override
    public List<UsuarioDto> getUsuarios() throws TakinaException {
        List<Usuario> UsuariosEntity = UsuarioRepository.findAll();
        return UsuariosEntity.stream().map(Usuario -> modelMapper.map(Usuario, UsuarioDto.class)).collect(Collectors.toList());
    }

    // -------------------------------------------------------
    @Override
    public UsuarioDto getUsuarioNombre(String nombre) throws TakinaException {
        return modelMapper.map(getUsuarioEntityNombre(nombre),UsuarioDto.class);
    }

    private Usuario getUsuarioEntityNombre(String nombre) throws TakinaException {
        return UsuarioRepository.findByNombre(nombre)
                .orElseThrow(()-> new NotFoundException("NOTFOUND-404","Usuario_NOTFOUND-404"));
    }

    // --------------------------------------------------------
    @Transactional
    @Override
    public UsuarioDto createUsuario(CreateUsuarioDto createUsuarioDto) throws TakinaException {
        Usuario Usuario = new Usuario();
		Usuario.setApodo(createUsuarioDto.getApodo());
        Usuario.setNombre(createUsuarioDto.getNombre());
        Usuario.setPassword(createUsuarioDto.getPassword());
        Usuario.setCorreo(createUsuarioDto.getCorreo());
        Usuario.setFotoPerfil(createUsuarioDto.getFotoPerfil());

        try {
            Usuario = UsuarioRepository.save(Usuario);
        }catch (Exception ex){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","USUARIO_NOT_CREATED");
        }
        return modelMapper.map(getUsuarioEntity(Usuario.getId()),UsuarioDto.class);
    }
}
