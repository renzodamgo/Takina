package com.backend.services;

import java.util.List;

import com.backend.dtos.PlaylistDto;
import com.backend.dtos.creates.CreatePlaylistDto;
import com.backend.dtos.edits.EditPlaylistDto;
import com.backend.exceptions.TakinaException;

public interface PlaylistService {
	List<PlaylistDto> getPlaylists() throws TakinaException;
	PlaylistDto addToPlaylist(Long playlistId, Long cancionId) throws TakinaException;
	PlaylistDto createPlaylist(CreatePlaylistDto createPlaylistDto) throws TakinaException;
	PlaylistDto getPlaylistById(Long playlistId) throws TakinaException;
	void deleteFromPlaylist(Long playlistId, Long cancionId) throws TakinaException;
	PlaylistDto editPlaylist(EditPlaylistDto editPlaylistDto) throws TakinaException;
	List<PlaylistDto> getPlaylistsByUserId(Long usuarioId) throws TakinaException;
}
