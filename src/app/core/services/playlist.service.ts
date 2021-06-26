import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { map } from 'rxjs/operators'
import { PlaylistResponse,PlaylistsResponse,PlaylistJson,Playlist,CreatePlaylist,EditPlaylist } from '../../models/playlist';

@Injectable({
	providedIn: 'root'
})
export class PlaylistService {

	constructor(private http:HttpClient) { }

	getPlaylistsByUsuarioId(usuarioId: number){
		const endpoint = `https://takina.herokuapp.com/takina/playlists/usuario/${usuarioId}`;
		return this.http.get<PlaylistsResponse>(endpoint)
			.pipe(map(response => {
				return response.data.map(playlist => Playlist.playlistFromJson(playlist));
			})
		);
	}

	addToPlaylistByIdAndCancionId(playlistId: number, cancionId: number){
		const endpoint = `https://takina.herokuapp.com/takina/playlists/${playlistId}/cancion/${cancionId}`;
		return this.http.get(endpoint);
	}

	deleteFromPlaylistByIdAndCancionId(playlistId: number, cancionId: number){
		const endpoint = `https://takina.herokuapp.com/takina/playlists/${playlistId}/cancion/${cancionId}`;
		return this.http.delete(endpoint);
	}

	getPlaylistById(playlistId: number) {
		const endpoint = `https://takina.herokuapp.com/takina/playlists/id/${playlistId}`;
		return this.http.get<PlaylistResponse>(endpoint)
			.pipe(map(resp => {
				return Playlist.playlistFromJson(resp.data);
			}
		));
	}

	deletePlaylistById(playlistId: number) {
		const endpoint = `https://takina.herokuapp.com/takina/playlists/id/${playlistId}`;
		return this.http.delete(endpoint);
	}

	createPlaylistByUserId(createPlaylist:CreatePlaylist) {
		const endpoint = `https://takina.herokuapp.com/takina/playlists`;
		return this.http.post<PlaylistResponse>(endpoint,createPlaylist);
	}

	editPlaylistById(editPlaylist:EditPlaylist){
		const endpoint = `https://takina.herokuapp.com/takina/playlists`;
		return this.http.put<PlaylistResponse>(endpoint,editPlaylist);
	}
}
