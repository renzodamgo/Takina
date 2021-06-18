import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { ArtistaResponse } from './models/artistaResponse';
import {map} from 'rxjs/operators'
import { Artista } from './models/artista';


@Injectable({
	providedIn: 'root'
})
export class ArtistaService {

	constructor(private http:HttpClient) { }

	getArtista(){
		const  endpoint = 'takina/artistas/id/1';
		return this.http.get<ArtistaResponse>(endpoint)
			.pipe(
				map( resp=>{
					// return resp.data.map(artista=> Artista.artistaFromJson(artista));
					return Artista.artistaFromJson(resp.data);
				})
			);
	}
}
