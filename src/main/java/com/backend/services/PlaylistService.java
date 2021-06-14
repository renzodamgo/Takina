package com.backend.services;

import java.util.List;

import com.backend.dtos.PlaylistDto;
import com.backend.dtos.creates.CreatePlaylistDto;
import com.backend.exceptions.TakinaException;

public interface PlaylistService {
	PlaylistDto getPlaylistById(Long playlistId) throws TakinaException;

	// Mostrar todas las playlists
	List<PlaylistDto> getPlaylists() throws TakinaException;

	// US007 - Como usuario Quiero crear una lista de reproduccion Para mantener mi libreria musical ordenada
	PlaylistDto createPlaylist(CreatePlaylistDto createPlaylistDto) throws TakinaException;

	// US008 - Como usuario Quiero añadir canciónes a mi lista de reproduccion Para escucharlas posteriormente junto a otras canciónes 
	PlaylistDto addToPlaylist(Long playlistId, Long cancionId) throws TakinaException;
	
	// US041 - Eliminar cancion del playlist
	void deleteFromPlaylist(Long playlistId, Long cancionId) throws TakinaException;
}
