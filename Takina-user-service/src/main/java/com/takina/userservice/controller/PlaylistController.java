package com.takina.userservice.controller;

import com.takina.userservice.dto.PlaylistDto;
import com.takina.userservice.dto.create.CreatePlaylistDto;
import com.takina.userservice.exceptions.PlaylistNotFoundException;
import com.takina.userservice.responses.TakinaResponse;
import com.takina.userservice.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/takina"+"/playlists")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    // Mostrar todas las playlists
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public TakinaResponse<List<PlaylistDto>> getPlaylists()
            throws PlaylistNotFoundException {
        return new TakinaResponse<>( "Success",String.valueOf(HttpStatus.OK),"OK",
                playlistService.getPlaylists());
    }

    // Crear playlist
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public TakinaResponse<PlaylistDto> createPlaylist(@RequestBody CreatePlaylistDto createPlaylistDto)
            throws PlaylistNotFoundException{
        return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                playlistService.createPlaylist(createPlaylistDto));
    }

    // Obtener por ID
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/id/{playlistId}")
    public TakinaResponse<PlaylistDto> getPlaylistById(@PathVariable Long playlistId)
            throws PlaylistNotFoundException{
        return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                playlistService.getPlaylistById(playlistId));
    }

    // Añadir cancion a playlist
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/adicion/{playlistId}/{cancionId}")
    public TakinaResponse<PlaylistDto> addToPlaylist(@PathVariable Long playlistId, @PathVariable Long cancionId)
            throws PlaylistNotFoundException{
        return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                playlistService.addToPlaylist(playlistId,cancionId));
    }


}