package com.backend.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.backend.dtos.LoginDto;
import com.backend.dtos.UsuarioDto;
import com.backend.dtos.creates.CreateUsuarioDto;
import com.backend.dtos.edits.EditUsuarioDto;
import com.backend.entities.Usuario;
import com.backend.exceptions.InternalServerErrorException;
import com.backend.exceptions.TakinaException;
import com.backend.exceptions.UsuarioNotFoundException;
import com.backend.repositories.UsuarioRepository;
import com.backend.services.UsuarioService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UsuarioDto getUsuarioId(Long UsuarioId) throws TakinaException {
		return modelMapper.map(getUsuarioEntity(UsuarioId),UsuarioDto.class);

	}

	private Usuario getUsuarioEntity(Long UsuarioId) throws TakinaException {
		return usuarioRepository.findById(UsuarioId)
				.orElseThrow(()-> new UsuarioNotFoundException("Usuario not found."));
	}

	@Override
	public List<UsuarioDto> getUsuarios() throws TakinaException {
		List<Usuario> usuariosEntity = usuarioRepository.findAll();
		return usuariosEntity.stream().map(Usuario -> modelMapper.map(Usuario, UsuarioDto.class)).collect(Collectors.toList());
	}

	@Override
	public UsuarioDto getUsuarioApodo(String apodo) throws TakinaException {
	 return modelMapper.map(getUsuarioEntityApodo(apodo),UsuarioDto.class);
	}
 
	private Usuario getUsuarioEntityApodo(String apodo) throws TakinaException {
	   return usuarioRepository.findByApodo(apodo)
			   .orElseThrow(()-> new UsuarioNotFoundException("Usuario not found."));
	}

	@Override
	public UsuarioDto getUsuarioCorreo(String correo) throws TakinaException {
	 return modelMapper.map(getUsuarioEntityCorreo(correo),UsuarioDto.class);
	}
 
	private Usuario getUsuarioEntityCorreo(String correo) throws TakinaException {
		return usuarioRepository.findByCorreo(correo)
				.orElseThrow(()-> new UsuarioNotFoundException("Usuario not found."));
	}

	@Transactional
	@Override
	public UsuarioDto createUsuario(CreateUsuarioDto createUsuarioDto) throws TakinaException {
		Usuario Usuario = new Usuario();
		Usuario.setApodo(createUsuarioDto.getApodo());
		Usuario.setNombre(createUsuarioDto.getNombre());
		Usuario.setPassword(createUsuarioDto.getPassword());
		Usuario.setCorreo(createUsuarioDto.getCorreo());
		Usuario.setFotoPerfil(createUsuarioDto.getFotoPerfil());
		Usuario.setUltimoIngreso(LocalDateTime.now());
		
		try {
			Usuario = usuarioRepository.save(Usuario);
		} catch (Exception ex) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","USUARIO_NOT_CREATED");
		}
		return modelMapper.map(getUsuarioEntity(Usuario.getId()),UsuarioDto.class);
	}

	@Override
	public List<UsuarioDto> getUsuariosByNombre(String nombre) throws TakinaException{
		List<Usuario> results = usuarioRepository.findByNombreContainingIgnoreCase(nombre);
		return results.stream().map(usuario -> modelMapper.map(usuario,UsuarioDto.class)).collect(Collectors.toList());
	}

	@Override
	public UsuarioDto loginUsuarioByApodoOrCorreoUsingPassword(LoginDto loginDto) throws TakinaException {
		Usuario Usuario = getUsuarioByApodoOrCorreo(loginDto.getLogin());
		
		Boolean encontrado = false;
		if (Usuario.getPassword().equals(loginDto.getPassword())) encontrado = true;

		if (!encontrado) throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","USUARIO_NOT_FOUND");
		
		return modelMapper.map(getUsuarioEntity(Usuario.getId()),UsuarioDto.class);
	}

	private Usuario getUsuarioByApodoOrCorreo(String login) throws TakinaException {
		return usuarioRepository.findByApodoOrCorreo(login)
				.orElseThrow(()-> new UsuarioNotFoundException("Usuario not found."));
	}

	@Override
	public UsuarioDto editUsuario(EditUsuarioDto editUsuarioDto) throws TakinaException {
		Usuario usuario = getUsuarioEntity(editUsuarioDto.getId());
		usuario.setApodo(editUsuarioDto.getApodo());
		usuario.setPassword(editUsuarioDto.getPassword());
		usuario.setNombre(editUsuarioDto.getNombre());
		usuario.setCorreo(editUsuarioDto.getCorreo());
		usuario.setFotoPerfil(editUsuarioDto.getFotoPerfil());

		try {
			usuario = usuarioRepository.save(usuario);
		} catch (Exception ex) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","USER_NOT_EDITED");
		}

		return modelMapper.map(usuario,UsuarioDto.class);
	}

	@Override
	public UsuarioDto togglePremium(Long usuarioId) throws TakinaException {
		Usuario usuario = getUsuarioEntity(usuarioId);
		usuario.setPremium(usuario.getPremium() ^ true);

		try {
			usuario = usuarioRepository.save(usuario);
		} catch (Exception ex) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","USUARIO_NOT_MODIFIED");
		}

		return modelMapper.map(usuario,UsuarioDto.class);
	}

	@Override
	public void deleteUser(Long usuarioId) throws TakinaException {
		Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);

		if (usuario.isPresent()) {
			// Eliminar artistas que no tengan otro administrador nivel Administrador
			// Eliminar playlists - listados
			usuarioRepository.deleteById(usuarioId);
		} else {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","USUARIO_NOT_FOUND");
		}
	}

}