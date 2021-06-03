package com.takina.userservice.services;

import com.takina.userservice.dto.PlaylistDto;
import com.takina.userservice.dto.create.CreatePlaylistDto;
import com.takina.userservice.exceptions.PlaylistNotFoundException;

import java.util.List;

public interface PlaylistService {
    PlaylistDto getPlaylistById(Long playlistId) throws PlaylistNotFoundException;

    // Mostrar todas las playlists
    List<PlaylistDto> getPlaylists() throws PlaylistNotFoundException;

    // US007 - Como usuario Quiero crear una lista de reproduccion Para mantener mi libreria musical ordenada
    PlaylistDto createPlaylist(CreatePlaylistDto createPlaylistDto) throws PlaylistNotFoundException;

    // US008 - Como usuario Quiero añadir canciónes a mi lista de reproduccion Para escucharlas posteriormente junto a otras canciónes
    PlaylistDto addToPlaylist(Long playlistId, Long cancionId) throws PlaylistNotFoundException;

}
