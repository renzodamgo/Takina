package com.backend.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.backend.dtos.ReproduccionDto;
import com.backend.dtos.UsuarioDto;
import com.backend.dtos.HistorialDto;
import com.backend.dtos.AsistenteDto;
import com.backend.dtos.SeguidorDto;
import com.backend.dtos.creates.CreateUsuarioDto;
import com.backend.dtos.edits.EditUsuarioDto;
import com.backend.entities.Artista;
import com.backend.entities.Usuario;
import com.backend.entities.Cancion;
import com.backend.entities.Evento;
import com.backend.entities.Reproduccion;
import com.backend.entities.Asistente;
import com.backend.entities.Seguidor;
import com.backend.exceptions.InternalServerErrorException;
import com.backend.exceptions.NotFoundException;
import com.backend.exceptions.TakinaException;
import com.backend.repositories.UsuarioRepository;
import com.backend.repositories.EventoRepository;
import com.backend.repositories.SeguidorRepository;
import com.backend.repositories.AdministradorRepository;
import com.backend.repositories.ArtistaRepository;
import com.backend.repositories.ReproduccionRepository;
import com.backend.repositories.AsistenteRepository;
import com.backend.repositories.CancionRepository;
import com.backend.services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private AsistenteRepository asistenteRepository;

	@Autowired
	private ArtistaRepository artistaRepository;

	@Autowired
	private CancionRepository cancionRepository;

	@Autowired
	private SeguidorRepository seguidorRepository;

	@Autowired
	private ReproduccionRepository reproduccionRepository;

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private AdministradorRepository administradorRepository;
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
		} catch (Exception ex) {
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
		Usuario Usuario = getUsuarioByApodoOrCorreo(login);
		
		Boolean encontrado = false;
		if (Usuario.getPassword().equals(password)) encontrado = true;
		if (!encontrado) throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","USUARIO_NOT_FOUND");
		
		return modelMapper.map(getUsuarioEntity(Usuario.getId()),UsuarioDto.class);
	}

	private Usuario getUsuarioByApodoOrCorreo(String login) throws TakinaException {
		return usuarioRepository.findByApodoOrCorreo(login,login)
				.orElseThrow(()-> new NotFoundException("NOTFOUND-404","Usuario_NOTFOUND-404"));
	}

	// --------------------------------------------------------
	@Transactional
	@Override
	public ReproduccionDto createReproduccion(Long usuarioId, Long cancionId) throws TakinaException {
		Usuario usuario = getUsuarioEntity(usuarioId);
		Cancion cancion = cancionRepository.findById(cancionId)
				.orElseThrow(()->new NotFoundException("NOT-401-1","CANCION_NOT_FOUND"));

		Reproduccion reproduccion = new Reproduccion();
		reproduccion.setUsuario(usuario);
		reproduccion.setCancion(cancion);
		reproduccion.setFecha(LocalDateTime.now());

		try {
			reproduccion = reproduccionRepository.save(reproduccion);
		} catch (Exception ex) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","REPRODUCCION_NOT_CREATED");
		}

		cancion.getProyecto().getArtista().setTotalReproducciones(
			cancion.getProyecto().getArtista().getTotalReproducciones()+1
		);

		return modelMapper.map(reproduccion,ReproduccionDto.class);
	}
	// --------------------------------------------------------
	@Override
	public HistorialDto getHistorial(Long usuarioId) throws TakinaException {
		List<Reproduccion> reproducciones = reproduccionRepository.findByUsuarioIdOrderByFecha(usuarioId);
											//.stream().limit(20).collect(Collectors.toList());

		List<Reproduccion> historial = new ArrayList<>();
		Integer cantidad = 20;
		for(int i = 0; i < reproducciones.size(); i++){
			if(historial.size() == cantidad) break;
			
			Boolean found = false;
			for(int j = 0; j < historial.size(); j++) {
				if(reproducciones.get(i).getUsuario().getId() == historial.get(j).getUsuario().getId() &&
					reproducciones.get(i).getCancion().getId() == historial.get(j).getCancion().getId()) {
					found = true;
					break;
				}
			}

			if(!found) historial.add(reproducciones.get(i));
		}

		HistorialDto history = new HistorialDto();
		history.setApodo(getUsuarioEntity(usuarioId).getApodo());
		history.setReproducciones(historial.stream()
										.map(reproduccion -> modelMapper.map(reproduccion,ReproduccionDto.class))
										.collect(Collectors.toList()));
		
		return history;
	}
	// --------------------------------------------------------
	@Transactional
	@Override
	public SeguidorDto createSeguidor(Long usuarioId, Long artistaId) throws TakinaException {
		Optional<Seguidor> validacion = seguidorRepository.findByUsuarioIdAndArtistaId(usuarioId, artistaId);
		
		if (validacion.isPresent()) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","SEGUIDOR_NOT_CREATED");
		}

		Usuario usuario = getUsuarioEntity(usuarioId);
		Artista artista = artistaRepository.findById(artistaId)
				.orElseThrow(()->new NotFoundException("NOT-401-1","ARTISTA_NOT_FOUND"));

		artista.setTotalSeguidores(artista.getTotalSeguidores()+1);

		Seguidor seguidor = new Seguidor();
		seguidor.setUsuario(usuario);
		seguidor.setArtista(artista);
		seguidor.setFecha(LocalDateTime.now());

		try {
			seguidor = seguidorRepository.save(seguidor);
		} catch (Exception ex) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","SEGUIDOR_NOT_CREATED");
		}

		return modelMapper.map(seguidor,SeguidorDto.class);
	}
	// --------------------------------------------------------
	@Override
	public void deleteSeguidor(Long usuarioId, Long artistaId) throws TakinaException {
		Optional<Seguidor> validacion = seguidorRepository.findByUsuarioIdAndArtistaId(usuarioId, artistaId);
		if (validacion.isPresent()) {
			seguidorRepository.deleteById(validacion.get().getId());

			Artista artista = artistaRepository.findById(artistaId)
				.orElseThrow(()->new NotFoundException("NOT-401-1","ARTISTA_NOT_FOUND"));
			
			artista.setTotalSeguidores(artista.getTotalSeguidores()-1);
		} else {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","SEGUIDOR_NOT_FOUND");
		}
	}
	// --------------------------------------------------------
	@Transactional
	@Override
	public AsistenteDto createAsistente(Long usuarioId, Long eventoId) throws TakinaException {
		Optional<Asistente> validacion = asistenteRepository.findByUsuarioIdAndEventoId(usuarioId, eventoId);
		
		if (validacion.isPresent()) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","SEGUIDOR_NOT_CREATED");
		}

		Usuario usuario = getUsuarioEntity(usuarioId);
		Evento evento = eventoRepository.findById(eventoId)
				.orElseThrow(()->new NotFoundException("NOT-401-1","ARTISTA_NOT_FOUND"));

		Asistente asistente = new Asistente();
		asistente.setUsuario(usuario);
		asistente.setEvento(evento);
		asistente.setFecha(LocalDateTime.now());

		try {
			asistente = asistenteRepository.save(asistente);
		} catch (Exception ex) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","SEGUIDOR_NOT_CREATED");
		}

		evento.setInteresados(evento.getInteresados()+1);

		return modelMapper.map(asistente,AsistenteDto.class);
	}
	// --------------------------------------------------------
	@Override
	public void deleteAsistente(Long usuarioId, Long eventoId) throws TakinaException {
		Optional<Asistente> validacion = asistenteRepository.findByUsuarioIdAndEventoId(usuarioId, eventoId);
		if (validacion.isPresent()) {
			asistenteRepository.deleteById(validacion.get().getId());

			Evento evento = eventoRepository.findById(eventoId)
				.orElseThrow(()->new NotFoundException("NOT-401-1","EVENTO_NOT_FOUND"));
			evento.setInteresados(evento.getInteresados()-1);

		} else {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","SEGUIDOR_NOT_FOUND");
		}
	}
	// --------------------------------------------------------
	@Override
	public UsuarioDto editUsuario(EditUsuarioDto editUsuarioDto) throws TakinaException {
		Usuario usuario = getUsuarioEntity(editUsuarioDto.getId());

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
	// --------------------------------------------------------
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
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","USUARIO_NOT_FOUND");
		}
	}

}