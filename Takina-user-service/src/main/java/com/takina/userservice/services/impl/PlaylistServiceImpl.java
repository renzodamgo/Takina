package com.takina.userservice.services.impl;

import com.takina.userservice.dto.PlaylistDto;
import com.takina.userservice.dto.create.CreatePlaylistDto;
import com.takina.userservice.entities.Playlist;
import com.takina.userservice.entities.Usuario;
import com.takina.userservice.exceptions.InternalServerErrorException;
import com.takina.userservice.exceptions.PlaylistNotFoundException;
import com.takina.userservice.repositories.ListadoRepository;
import com.takina.userservice.repositories.PlaylistRepository;
import com.takina.userservice.repositories.UsuarioRepository;
import com.takina.userservice.services.PlaylistService;
import com.takina.userservice.util.PlaylistConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    @Autowired
    PlaylistConverter playlistConverter;

    @Autowired
    PlaylistRepository playlistRepository;

    @Autowired
    private ListadoRepository listadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public PlaylistDto getPlaylistById(Long playlistId) throws PlaylistNotFoundException {
        return playlistConverter.convertPlaylistToDto(getPlaylistEntity(playlistId));
    }
    private Playlist getPlaylistEntity(Long playlistId) throws PlaylistNotFoundException {
        return playlistRepository.findById(playlistId)
                .orElseThrow(()-> new PlaylistNotFoundException("NOTFOUND-404 PLAYLIST_NOTFOUND-404 by id" + playlistId));
    }
    @Override
    public List<PlaylistDto> getPlaylists() throws PlaylistNotFoundException {
        List<Playlist> playlists = playlistRepository.findAll();
        return playlists.stream().map(playlist -> playlistConverter.convertPlaylistToDto(playlist)).collect(Collectors.toList());

    }

    @Override
    public PlaylistDto createPlaylist(CreatePlaylistDto createPlaylistDto) throws PlaylistNotFoundException {
        Usuario usuario = usuarioRepository.findById(createPlaylistDto.getUsuarioId())
                .orElseThrow(()->new PlaylistNotFoundException("NOT-401-1 USUARIO_NOT_FOUND by id: "+ createPlaylistDto.getUsuarioId()));

        Playlist playlist = new Playlist();
        playlist.setNombre(createPlaylistDto.getNombre());
        playlist.setDescripcion(createPlaylistDto.getDescripcion());
        playlist.setCreacion(LocalDateTime.now());;
        playlist.setUsuario(usuario);

        try {
            playlist = playlistRepository.save(playlist);
        } catch (Exception ex) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR PLAYLIST_NOT_CREATED");
        }
        return playlistConverter.convertPlaylistToDto(getPlaylistEntity(playlist.getId()));
    }

    @Override
    public PlaylistDto addToPlaylist(Long playlistId, Long cancionId) throws PlaylistNotFoundException {
        return null;
    }
}
