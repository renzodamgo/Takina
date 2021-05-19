package com.backend.controllers;

import java.util.List;

import com.backend.dtos.PlaylistDto;
import com.backend.services.PlaylistService;
import com.backend.dtos.creates.CreatePlaylistDto;

import com.backend.exceptions.TakinaException;
import com.backend.responses.TakinaResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(path = "/takina"+"/playlists")
public class PlaylistController {
	@Autowired
	private PlaylistService playlistService;

	// Obtener todas las playlists
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public TakinaResponse<List<PlaylistDto>> getPlaylists()
			throws TakinaException {
		return new TakinaResponse<>( "Success",String.valueOf(HttpStatus.OK),"OK",
				playlistService.getPlaylists());
	}

	// Crear playlist
	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	public TakinaResponse<PlaylistDto> createPlaylist(@RequestBody CreatePlaylistDto createPlaylistDto)
			throws TakinaException{
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				playlistService.createPlaylist(createPlaylistDto));
	}

	// Obtener por ID
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/id/{playlistId}")
	public TakinaResponse<PlaylistDto> getPlaylistById(@PathVariable Long playlistId)
			throws TakinaException{
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				playlistService.getPlaylistById(playlistId));
	}

	// Añadir cancion a playlist
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/id/{playlistId}/cancion/{cancionId}")
	public TakinaResponse<PlaylistDto> addToPlaylist(@PathVariable Long playlistId, @PathVariable Long cancionId)
			throws TakinaException{
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				playlistService.addToPlaylist(playlistId,cancionId));
	}


}
