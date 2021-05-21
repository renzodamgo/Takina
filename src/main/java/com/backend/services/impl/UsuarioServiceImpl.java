package com.backend.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.backend.dtos.ReproduccionDto;
import com.backend.dtos.UsuarioDto;
import com.backend.dtos.creates.CreateUsuarioDto;
import com.backend.entities.Usuario;
import com.backend.entities.Cancion;
import com.backend.entities.Reproduccion;
import com.backend.entities.Asistente;
import com.backend.entities.Seguidor;
import com.backend.exceptions.InternalServerErrorException;
import com.backend.exceptions.NotFoundException;
import com.backend.exceptions.TakinaException;
import com.backend.repositories.UsuarioRepository;
import com.backend.repositories.SeguidorRepository;
import com.backend.repositories.ReproduccionRepository;
import com.backend.repositories.AsistenteRepository;
import com.backend.repositories.CancionRepository;
import com.backend.services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private AsistenteRepository asistenteRepository;

	@Autowired
	private CancionRepository cancionRepository;

	@Autowired
	private SeguidorRepository seguidorRepository;

	@Autowired
	private ReproduccionRepository reproduccionRepository;
	// -------------------------------------------------------
	@Override
	public UsuarioDto getUsuarioId(Long UsuarioId) throws TakinaException {
		return modelMapper.map(getUsuarioEntity(UsuarioId),UsuarioDto.class);

	}

	private Usuario getUsuarioEntity(Long UsuarioId) throws TakinaException {
		return usuarioRepository.findById(UsuarioId)
				.orElseThrow(()-> new NotFoundException("NOTFOUND-404","Usuario_NOTFOUND-404"));
	}


	// -------------------------------------------------------
	@Override
	public List<UsuarioDto> getUsuarios() throws TakinaException {
		List<Usuario> usuariosEntity = usuarioRepository.findAll();
		return usuariosEntity.stream().map(Usuario -> modelMapper.map(Usuario, UsuarioDto.class)).collect(Collectors.toList());
	}

	// -------------------------------------------------------
	@Override
	public UsuarioDto getUsuarioNombre(String nombre) throws TakinaException {
		return modelMapper.map(getUsuarioEntityNombre(nombre),UsuarioDto.class);
	}

	private Usuario getUsuarioEntityNombre(String nombre) throws TakinaException {
		return usuarioRepository.findByNombre(nombre)
				.orElseThrow(()-> new NotFoundException("NOTFOUND-404","Usuario_NOTFOUND-404"));
	}

	// -------------------------------------------------------
	@Override
	public UsuarioDto getUsuarioApodo(String apodo) throws TakinaException {
	 return modelMapper.map(getUsuarioEntityApodo(apodo),UsuarioDto.class);
	}
 
	private Usuario getUsuarioEntityApodo(String apodo) throws TakinaException {
	   return usuarioRepository.findByApodo(apodo)
			   .orElseThrow(()-> new NotFoundException("NOTFOUND-404","Usuario_NOTFOUND-404"));
	}

	// -------------------------------------------------------
	@Override
	public UsuarioDto getUsuarioCorreo(String correo) throws TakinaException {
	 return modelMapper.map(getUsuarioEntityCorreo(correo),UsuarioDto.class);
	}
 
	private Usuario getUsuarioEntityCorreo(String correo) throws TakinaException {
		return usuarioRepository.findByCorreo(correo)
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
		Usuario.setUltimoIngreso(LocalDateTime.now());
		
		try {
			Usuario = usuarioRepository.save(Usuario);
		}catch (Exception ex){
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","USUARIO_NOT_CREATED");
		}
		return modelMapper.map(getUsuarioEntity(Usuario.getId()),UsuarioDto.class);
	}

	// --------------------------------------------------------
	@Override
	public List<UsuarioDto> getUsuariosByNombre(String nombre) throws TakinaException{
		List<Usuario> results = usuarioRepository.findByNombreContainingIgnoreCase(nombre);
		return results.stream().map(usuario -> modelMapper.map(usuario,UsuarioDto.class)).collect(Collectors.toList());
	}

	// --------------------------------------------------------
	@Override
	public UsuarioDto loginUsuarioByApodoOrCorreoUsingPassword(String login, String password) throws TakinaException {
		Boolean encontrado = false;
		Usuario Usuario = getUsuarioByApodoOrCorreo(login);
		
		if (Usuario.getPassword().equals(password)) {
			encontrado = true;
		}

		if (!encontrado) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","USUARIO_NOT_FOUND");
		}
		
		return modelMapper.map(getUsuarioEntity(Usuario.getId()),UsuarioDto.class);
	}

	private Usuario getUsuarioByApodoOrCorreo(String login) throws TakinaException {
		return usuarioRepository.findByApodoOrCorreo(login,login)
				.orElseThrow(()-> new NotFoundException("NOTFOUND-404","Usuario_NOTFOUND-404"));
	}

	// --------------------------------------------------------
	@Transactional
	@Override
	public ReproduccionDto createReproduccion (Long usuarioId, Long cancionId) throws TakinaException {
		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(()->new NotFoundException("NOT-401-1","USUARIO_NOT_FOUND"));
		
		Cancion cancion = cancionRepository.findById(cancionId)
				.orElseThrow(()->new NotFoundException("NOT-401-1","CANCION_NOT_FOUND"));

		Reproduccion reproduccion = new Reproduccion();
		reproduccion.setUsuario(usuario);
		reproduccion.setCancion(cancion);
		reproduccion.setFecha(LocalDateTime.now());

		try {
			reproduccion = reproduccionRepository.save(reproduccion);
		}catch (Exception ex) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","USUARIO_NOT_CREATED");
		}
		
		return modelMapper.map(reproduccion,ReproduccionDto.class);
	}
}
