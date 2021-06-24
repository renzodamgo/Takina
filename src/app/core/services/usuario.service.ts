import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { map } from 'rxjs/operators'
import { Usuario, UsuarioResponse, CreateUsuario, EditUsuario, LoginUsuario } from '../../models/usuario';

@Injectable({
	providedIn: 'root'
})
export class UsuarioService {

	constructor(private http:HttpClient) { }

	getUsuarioById(usuarioId: number) {
		const endpoint = `https://takina.herokuapp.com/takina/usuarios/id/${usuarioId}`;
		return this.http.get<UsuarioResponse>(endpoint)
			.pipe(
				map( resp=>{
					return Usuario.usuarioFromJson(resp.data);
				})
			);
	}

	createUsuario(createUsuario: CreateUsuario) {
		const endpoint = `https://takina.herokuapp.com/takina/usuarios`;
    	return this.http.post<UsuarioResponse>(endpoint, createUsuario).pipe(
			map(resp=>{
				return Usuario.usuarioFromJson(resp.data);
			})
		);
	}

	editUsuario(editUsuario: EditUsuario) {
		const endpoint = `https://takina.herokuapp.com/takina/usuarios`;
    	return this.http.post<UsuarioResponse>(endpoint, editUsuario).pipe(
			map(resp=>{
				return Usuario.usuarioFromJson(resp.data);
			})
		);
	}

	loginUsuario(loginUsuario: LoginUsuario) {
		const endpoint = `https://takina.herokuapp.com/takina/usuarios/login`;
    	return this.http.post<UsuarioResponse>(endpoint, loginUsuario).pipe(
			map(resp=>{
				return Usuario.usuarioFromJson(resp.data);
			})
		);
	}

	// Playlists:
}
