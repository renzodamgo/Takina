package com.takina.userservice.services.impl;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import com.takina.userservice.dto.UsuarioDto;
import com.takina.userservice.dto.create.CreateUsuarioDto;
import com.takina.userservice.dto.edit.EditUsuarioDto;
import com.takina.userservice.entities.Usuario;
import com.takina.userservice.exceptions.InternalServerErrorException;
import com.takina.userservice.exceptions.UserNotFoundException;
import com.takina.userservice.repositories.UsuarioRepository;
import com.takina.userservice.services.UsuarioService;
import com.takina.userservice.util.UsuarioConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    //private static Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioConverter usuarioConverter;

    @Override
    public List<UsuarioDto> getUsuarios() throws UserNotFoundException {
        List<Usuario> usuariosEntity = usuarioRepository.findAll();
        return usuariosEntity.stream().map(usuario -> usuarioConverter.convertUsuarioToDto(usuario)).collect(Collectors.toList());
    }

    @Override
    public UsuarioDto getUsuarioId(Long usuarioId) throws UserNotFoundException {
        Usuario usuario = usuarioRepository.getById(usuarioId);
        return usuarioConverter.convertUsuarioToDto(usuario);
    }

    @Override
    public UsuarioDto getUsuarioApodo(String apodo) throws UserNotFoundException {
        return usuarioConverter.convertUsuarioToDto(getUsuarioEntityApodo(apodo));
    }

    private Usuario getUsuarioEntityApodo(String apodo) throws UserNotFoundException {
        return usuarioRepository.findByApodo(apodo)
                .orElseThrow(()-> new UserNotFoundException("NOTFOUND-404 Usuario_NOTFOUND-404 by apodo: "+ apodo));
    }

    @Override
    public UsuarioDto getUsuarioCorreo(String correo) throws UserNotFoundException {
        return usuarioConverter.convertUsuarioToDto(getUsuarioEntityCorreo(correo));
    }

    private Usuario getUsuarioEntityCorreo(String correo) throws UserNotFoundException {
        return usuarioRepository.findByCorreo(correo)
                .orElseThrow(()-> new UserNotFoundException("NOTFOUND-404 Usuario_NOTFOUND-404 by correo: "+ correo));
    }

    @Override
    public UsuarioDto getUsuarioNombre(String nombre) throws UserNotFoundException {
        return usuarioConverter.convertUsuarioToDto(getUsuarioEntityNombre(nombre));
    }

    private Usuario getUsuarioEntityNombre(String nombre) throws UserNotFoundException {
        return usuarioRepository.findByNombre(nombre)
                .orElseThrow(()-> new UserNotFoundException("NOTFOUND-404 Usuario_NOTFOUND-404 by name: "+nombre));
    }

    @Override
    public UsuarioDto createUsuario(CreateUsuarioDto createUsuarioDto) throws UserNotFoundException {
        Usuario usuario = new Usuario();
        usuario.setApodo(createUsuarioDto.getApodo());
        usuario.setNombre(createUsuarioDto.getNombre());
        usuario.setPassword(createUsuarioDto.getPassword());
        usuario.setCorreo(createUsuarioDto.getCorreo());
        usuario.setFotoPerfil(createUsuarioDto.getFotoPerfil());
        usuario.setUltimoIngreso(LocalDateTime.now());
        try {
            usuario = usuarioRepository.save(usuario);
        } catch (Exception ex) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR - USUARIO_NOT_CREATED");
        }
        return usuarioConverter.convertUsuarioToDto(getUsuarioEntity(usuario.getId()));
    }

    private Usuario getUsuarioEntity(Long UsuarioId) throws UserNotFoundException {
        return usuarioRepository.findById(UsuarioId)
                .orElseThrow(()-> new UserNotFoundException("NOTFOUND-404 Usuario_NOTFOUND-404"));
    }

    @Override
    public UsuarioDto loginUsuarioByApodoOrCorreoUsingPassword(String login, String password) throws UserNotFoundException {
        Usuario usuario = getUsuarioByApodoOrCorreo(login);

        Boolean encontrado = false;
        if (usuario.getPassword().equals(password)) encontrado = true;
        if (!encontrado) throw new UserNotFoundException("USUARIO_NOT_FOUND");

        return usuarioConverter.convertUsuarioToDto(usuario);
    }

    private Usuario getUsuarioByApodoOrCorreo(String login) throws UserNotFoundException {
        return usuarioRepository.findByApodoOrCorreo(login,login)
                .orElseThrow(()-> new UserNotFoundException("NOTFOUND-404 Usuario_NOTFOUND-404 by apodo or correo: "+login));
    }

    @Override
    public List<UsuarioDto> getUsuariosByNombre(String nombre) throws UserNotFoundException {
        List<Usuario> results = usuarioRepository.findByNombreContainingIgnoreCase(nombre);
        return results.stream().map(usuario -> usuarioConverter.convertUsuarioToDto(usuario)).collect(Collectors.toList());
    }

    @Override
    public UsuarioDto editUsuario(EditUsuarioDto editUsuarioDto) throws UserNotFoundException {
        Usuario usuario = getUsuarioEntity(editUsuarioDto.getId());

        usuario.setPassword(editUsuarioDto.getPassword());
        usuario.setNombre(editUsuarioDto.getNombre());
        usuario.setCorreo(editUsuarioDto.getCorreo());
        usuario.setFotoPerfil(editUsuarioDto.getFotoPerfil());

        try {
            usuario = usuarioRepository.save(usuario);
        } catch (Exception ex) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR USER_NOT_EDITED");
        }

        return usuarioConverter.convertUsuarioToDto(usuario);
    }

    @Override
    public UsuarioDto togglePremium(Long usuarioId) throws UserNotFoundException {
        Usuario usuario = getUsuarioEntity(usuarioId);
        usuario.setPremium(usuario.getPremium() ^ true);

        try {
            usuario = usuarioRepository.save(usuario);
        } catch (Exception ex) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR USUARIO_NOT_MODIFIED");
        }

        return usuarioConverter.convertUsuarioToDto(usuario);
    }

    @Override
    public void deleteUser(Long usuarioId) throws UserNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);

        if (usuario.isPresent()) {
            // Eliminar artistas que no tengan otro administrador nivel Administrador (No funciona)
            //List<Long> artistasId = new ArrayList<>();
            //for(Administrador a : administradorRepository.findByUsuarioIdAndNivel(usuarioId,"Administrador")) {
            //	Integer count = 0;
            //	for(Administrador adm : a.getArtista().getAdministradores()) {
            //		if (adm.getUsuario().getId().equals(usuarioId)) continue;
            //		if (adm.getNivel().equals("Administrador")) count++;
            //	}
            //	if(count.equals(0)) artistasId.add(a.getArtista().getId());
            //}
            //for(Long id : artistasId) artistaRepository.deleteById(id);

            usuarioRepository.deleteById(usuarioId);
        } else {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR USUARIO_NOT_FOUND by id: "+usuarioId);
        }
    }
}
