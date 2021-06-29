import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { map } from 'rxjs/operators'
import { CancionResponse,CancionesResponse,CancionJson,Cancion,CreateCancion } from '../../models/cancion';

@Injectable({
	providedIn: 'root'
})
export class CancionService {

	path: string = "https://takina.herokuapp.com/takina/canciones";
	//path: string = "http://localhost:8090/takina/canciones"

	constructor(private http:HttpClient) { }

	getCancionesByProyectoId(proyectoId: number){
		const endpoint = this.path+`/proyecto/${proyectoId}`;
		return this.http.get<CancionesResponse>(endpoint)
			.pipe(map(response => {
				return response.data.map(cancion => Cancion.cancionFromJson(cancion));
			})
		);
	}

	addCancion(createCancion:CreateCancion) {
		const endpoint = this.path;
		return this.http.post<CancionResponse>(endpoint,createCancion);
	}

	deleteCancion(cancionId: number){
		const endpoint = this.path+`/id/${cancionId}`;
		return this.http.delete(endpoint);
	}

	playCancion(usuarioId:number, cancionId: number){
		const endpoint = this.path+`/reproduccion/${usuarioId}/${cancionId}`;
		return this.http.get(endpoint);
	}

	getCancionesByPlaylistId(playlistId: number){
		const endpoint = this.path+`/playlist/${playlistId}`;
		return this.http.get<CancionesResponse>(endpoint)
			.pipe(map(response => {
				return response.data.map(cancion => Cancion.cancionFromJson(cancion));
			})
		);
	}

	getCancionesByNombre(cancionNombre: string){
		const endpoint = this.path+`/nombre/${cancionNombre}`;
		return this.http.get<CancionesResponse>(endpoint)
			.pipe(map(response => {
				return response.data.map(cancion => Cancion.cancionFromJson(cancion));
			})
		);
	}

}
