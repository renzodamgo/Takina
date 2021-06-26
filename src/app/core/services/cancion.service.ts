import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { map } from 'rxjs/operators'
import { CancionResponse,CancionesResponse,CancionJson,Cancion,CreateCancion } from '../../models/cancion';

@Injectable({
	providedIn: 'root'
})
export class CancionService {

	constructor(private http:HttpClient) { }

	getCancionesByProyectoId(proyectoId: number){
		const endpoint = `https://takina.herokuapp.com/takina/canciones/proyecto/${proyectoId}`;
		return this.http.get<CancionesResponse>(endpoint)
			.pipe(map(response => {
				return response.data.map(cancion => Cancion.cancionFromJson(cancion));
			})
		);
	}

	addCancion(createCancion:CreateCancion) {
		const endpoint = `https://takina.herokuapp.com/takina/canciones`;
		return this.http.post<CancionResponse>(endpoint,createCancion);
	}

	deleteCancion(cancionId: number){
		const endpoint = `https://takina.herokuapp.com/takina/canciones/id/${cancionId}`;
		return this.http.delete(endpoint);
	}

	playCancion(usuarioId:number, cancionId: number){
		const endpoint = `https://takina.herokuapp.com/takina/canciones/reproduccion/${usuarioId}/${cancionId}`;
		return this.http.get(endpoint);
	}

	getCancionesByPlaylistId(playlistId: number){
		const endpoint = `https://takina.herokuapp.com/takina/canciones/playlist/${playlistId}`;
		return this.http.get<CancionesResponse>(endpoint)
			.pipe(map(response => {
				return response.data.map(cancion => Cancion.cancionFromJson(cancion));
			})
		);
	}

}
