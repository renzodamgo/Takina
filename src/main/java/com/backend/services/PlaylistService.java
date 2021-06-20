package com.backend.services;

import java.util.List;

import com.backend.dtos.PlaylistDto;
import com.backend.dtos.creates.CreatePlaylistDto;
import com.backend.exceptions.TakinaException;

public interface PlaylistService {
	PlaylistDto getPlaylistById(Long playlistId) throws TakinaException;

	List<PlaylistDto> getPlaylists() throws TakinaException;

	PlaylistDto createPlaylist(CreatePlaylistDto createPlaylistDto) throws TakinaException;

	PlaylistDto addToPlaylist(Long playlistId, Long cancionId) throws TakinaException;

	void deleteFromPlaylist(Long playlistId, Long cancionId) throws TakinaException;
}
