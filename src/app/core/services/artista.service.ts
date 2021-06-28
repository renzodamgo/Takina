import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ArtistaResponse, ArtistasResponse } from '../../models/artistaResponse';
import { map } from 'rxjs/operators'
import { Artista, CreateArtista } from '../../models/artista';

@Injectable({
	providedIn: 'root'
})
export class ArtistaService {

	path: string = "https://takina.herokuapp.com/takina/artistas";
	//path: string = "http://localhost:8088/takina/artistas";

	constructor(private http:HttpClient) { }

	getArtista(){
		const  endpoint = this.path+'/id/1';
		return this.http.get<ArtistaResponse>(endpoint)
			.pipe(
				map(resp => {
					// return resp.data.map(artista=> Artista.artistaFromJson(artista));
					return Artista.artistaFromJson(resp.data);
				})
			);
	}

	getArtistaById(artistaId: number) {
		const  endpoint = this.path+`/id/${artistaId}`;
		return this.http.get<ArtistaResponse>(endpoint)
			.pipe(
				map(resp => {
					return Artista.artistaFromJson(resp.data);
				}
			)
		);
	}

	getArtistasByUserId(usuarioId: number) {
		const  endpoint = this.path+`/administrador/${usuarioId}`;
		return this.http.get<ArtistasResponse>(endpoint)
			.pipe(
				map(resp => {
					return resp.data.map(artista => Artista.artistaFromJson(artista));
				}
			)
		);
	}

	createArtistaByUserId(createArtista: CreateArtista){
		const endpoint = this.path;
		return this.http.post<ArtistaResponse>(endpoint,createArtista);
	}

	deleteArtistaById(artistaId: number){
		const endpoint = this.path+`/id/${artistaId}`;
		return this.http.delete(endpoint);
	}

}
