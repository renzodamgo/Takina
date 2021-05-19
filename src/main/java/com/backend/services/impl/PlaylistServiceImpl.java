package com.backend.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import com.backend.exceptions.InternalServerErrorException;
import com.backend.exceptions.NotFoundException;
import com.backend.exceptions.TakinaException;

import com.backend.dtos.PlaylistDto;
import com.backend.dtos.creates.CreatePlaylistDto;
import com.backend.entities.Playlist;
import com.backend.repositories.PlaylistRepository;
import com.backend.services.PlaylistService;

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

	// ----------------------------------------------------------------
	@Override
	public PlaylistDto getPlaylistById(Long playlistId) throws TakinaException {
		return modelMapper.map(getPlaylistEntity(playlistId), PlaylistDto.class);  
	}
	private Object getPlaylistEntity(Long playlistId) throws NotFoundException {
		return playlistRepository.findById(playlistId)
				.orElseThrow(()-> new NotFoundException("NOTFOUND-404","PLAYLIST_NOTFOUND-404"));
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
				.orElseThrow(()->new NotFoundException("NOT-401-1","USUARIO_NOT_FOUND"));

		Playlist playlist = new Playlist();
		playlist.setNombre(createPlaylistDto.getNombre());
		playlist.setDescripcion(createPlaylistDto.getDescripcion());
		playlist.setCreacion(LocalDateTime.now());
		playlist.setDuracion(0F);
		playlist.setNumeroCanciones(0);
		playlist.setUsuario(usuario);

		try {
			playlist = playlistRepository.save(playlist);
		} catch (Exception ex) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","PLAYLIST_NOT_CREATED");
		}

		usuario.getPlaylists().add(playlist);

		return modelMapper.map(getPlaylistEntity(playlist.getId()),PlaylistDto.class);
	}

	

}
