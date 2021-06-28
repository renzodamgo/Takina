import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { Cancion } from 'src/app/models/cancion';
import { Canciones, Proyecto } from '../../models/projecto';
import { Projecto, ProjectoResponse, ProjectosResponse  } from '../../models/proyectResponse';

@Injectable({
	providedIn: 'root'
})
export class ProyectoService {
	constructor(private http:HttpClient) { }

	path: string = "https://takina.herokuapp.com/takina/proyectos";
	//path: string = "http://localhost:8090/takina/proyectos"

	getProyectos(artistaId:number){
		const endpoint = this.path+`/artista/${artistaId}`;
		return this.http.get<ProjectosResponse>(endpoint)
			.pipe(map(resp => {
			    	return resp.data.map(proyecto => Proyecto.proyectoFromJson(proyecto));
			    }
			)
		);
	}

	getProyectoById(proyectoId:number){
		const endpoint = this.path+`/id/${proyectoId}`;
		return this.http.get<ProjectoResponse>(endpoint)
			.pipe(map(resp => {
			    	return Proyecto.proyectoFromJson(resp.data);
				}
			)
		);
	}

	deleteProyectoById(proyectoId:number) {
		const endpoint = this.path+`/id/${proyectoId}`;
		return this.http.delete(endpoint);
	}

	updateProjectoById( project:Proyecto){
		const endpoint = this.path;
		return this.http.put<Projecto>(endpoint,project);
	}

	addProjecto(project: Proyecto) {
		const endpoint = this.path;
		return this.http.post<ProjectoResponse>(endpoint, project);
	}

	getUltimosProyectos(){
		const endpoint = this.path+`/ultimos`;
		return this.http.get<ProjectosResponse>(endpoint)
			.pipe(map(resp => {
			    	return resp.data.map(proyecto => Proyecto.proyectoFromJson(proyecto));
			    }
			  )
		);
	}
}
