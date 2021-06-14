package com.backend.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import com.backend.exceptions.InternalServerErrorException;
import com.backend.exceptions.NotFoundException;
import com.backend.exceptions.TakinaException;
import com.backend.exceptions.PlaylistNotFoundException;
import com.backend.exceptions.UsuarioNotFoundException;
import com.backend.exceptions.CancionNotFoundException;
import com.backend.exceptions.ListadoNotFoundException;
import com.backend.dtos.PlaylistDto;
import com.backend.dtos.creates.CreatePlaylistDto;
import com.backend.entities.Playlist;
import com.backend.repositories.PlaylistRepository;
import com.backend.services.PlaylistService;
import com.backend.entities.Listado;
import com.backend.repositories.ListadoRepository;
import com.backend.entities.Cancion;
import com.backend.repositories.CancionRepository;
import com.backend.entities.Usuario;
import com.backend.repositories.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaylistServiceImpl implements PlaylistService {
	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private PlaylistRepository playlistRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ListadoRepository listadoRepository;

	@Autowired
	private CancionRepository cancionRepository;

	// ----------------------------------------------------------------
	@Override
	public PlaylistDto getPlaylistById(Long playlistId) throws TakinaException {
		return modelMapper.map(getPlaylistEntity(playlistId), PlaylistDto.class);  
	}
	private Playlist getPlaylistEntity(Long playlistId) throws NotFoundException {
		return playlistRepository.findById(playlistId)
				.orElseThrow(()-> new PlaylistNotFoundException("Playlist not found."));
	}

	// ----------------------------------------------------------------
	@Override
	public List<PlaylistDto> getPlaylists() throws TakinaException {
		List<Playlist> playlists = playlistRepository.findAll();
		return playlists.stream().map(playlist -> modelMapper.map(playlist, PlaylistDto.class)).collect(Collectors.toList());
	}

	// ----------------------------------------------------------------
	@Transactional
	@Override
	public PlaylistDto createPlaylist(CreatePlaylistDto createPlaylistDto) throws TakinaException {
		Usuario usuario = usuarioRepository.findById(createPlaylistDto.getUsuarioId())
				.orElseThrow(()->new UsuarioNotFoundException("Usuario not found."));

		Playlist playlist = new Playlist();
		playlist.setNombre(createPlaylistDto.getNombre());
		playlist.setDescripcion(createPlaylistDto.getDescripcion());
		playlist.setCreacion(LocalDateTime.now());;
		playlist.setUsuario(usuario);

		try {
			playlist = playlistRepository.save(playlist);
		} catch (Exception ex) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","PLAYLIST_NOT_CREATED");
		}
		//usuario.getPlaylists().add(playlist);

		return modelMapper.map(getPlaylistEntity(playlist.getId()),PlaylistDto.class);
	}
	// ----------------------------------------------------------------
	@Transactional
	@Override
	public PlaylistDto addToPlaylist(Long playlistId, Long cancionId) throws TakinaException {
		Optional<Listado> validacion = listadoRepository.findByPlaylistIdAndCancionId(playlistId,cancionId);

		if (validacion.isPresent()) {
			throw new ListadoNotFoundException("Cancion already found in Playlist.");
		}

		Playlist playlist = getPlaylistEntity(playlistId);

		Cancion cancion = cancionRepository.findById(cancionId)
				.orElseThrow(()->new CancionNotFoundException("Cancion not found."));

		Listado listado = new Listado();
		listado.setCancion(cancion);
		listado.setPlaylist(playlist);
		listado.setFechaAdicion(LocalDateTime.now());
		
		try {
			listado = listadoRepository.save(listado);
		} catch (Exception ex) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","LISTADO_NOT_CREATED");
		}

		playlist.setDuracion(playlist.getDuracion()+cancion.getDuracion());
		playlist.setNumCanciones(playlist.getNumCanciones()+1);
		
		return modelMapper.map(getPlaylistEntity(playlist.getId()),PlaylistDto.class);
	}

	@Override
	public void deleteFromPlaylist(Long playlistId, Long cancionId) throws TakinaException {
		Optional<Listado> validacion = listadoRepository.findByPlaylistIdAndCancionId(playlistId,cancionId);

		if (validacion.isPresent()) {
			listadoRepository.deleteById(validacion.get().getId());
		} else {
			throw new ListadoNotFoundException("Cancion not found in Playlist.");
		}
	}

	

}
