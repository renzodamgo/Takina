import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Cancion } from 'src/app/models/cancion';
import { Canciones, Proyecto } from '../../models/projecto';
import { Projecto, ProjectoResponse, ProjectosResponse  } from '../../models/proyectResponse';

@Injectable({
  providedIn: 'root'
})
export class ProyectoService {

  constructor(private http:HttpClient) { }

  getProyectos(artistaId:number){
    const  endpoint = `https://takina.herokuapp.com/takina/proyectos/artista/${artistaId}`;
    return this.http.get<ProjectosResponse>(endpoint)
      .pipe(
        map(
          resp => {
            // return resp.data
            return resp.data.map(proyecto => Proyecto.proyectoFromJson(proyecto));
          }
        )
    );
  }

  getProyectoById(proyectoId:number){
    const  endpoint = `https://takina.herokuapp.com/takina/proyectos/id/${proyectoId}`;
    return this.http.get<ProjectoResponse>(endpoint)
      .pipe(
        map(
          resp => {
            return  Proyecto.proyectoFromJson(resp.data);
          }
        )
      );
  }

  deleteProyectoById(proyectoId:number) {
    const  endpoint = `https://takina.herokuapp.com/takina/proyectos/id/${proyectoId}`;
    return this.http.delete(endpoint);
  }

  updateProjectoById( project:Proyecto){
    const  endpoint = `https://takina.herokuapp.com/takina/proyectos`;
    return this.http.put<Projecto>(endpoint,project);

  }

  addProjecto(project: Proyecto) {
    const  endpoint = `https://takina.herokuapp.com/takina/proyectos`;
    return this.http.post<ProjectoResponse>(endpoint, project);
  }

  addCancionByProjectoId(cancion: Cancion):Observable<Cancion> {
    const  endpoint = `https://takina.herokuapp.com/takina/canciones`;
    return this.http.post<Cancion>(endpoint,cancion);
  }
}
