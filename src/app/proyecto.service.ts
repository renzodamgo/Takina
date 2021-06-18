import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Proyecto } from './models/projecto';

import { ProjectoResponse  } from './models/proyectResponse';


@Injectable({
  providedIn: 'root'
})
export class ProyectoService {

  constructor(private http:HttpClient) { }

  getProyectos(artistaId:number){
    const  endpoint = `takina/proyectos/artista/${artistaId}`;
    return this.http.get<ProjectoResponse>(endpoint)
      .pipe(
        map(
          resp => {
            // return resp.data
            return resp.data.map(proyecto => Proyecto.proyectoFromJson(proyecto));
          }
        )
      );
  }

  getProyectoById(proyectoId:number):Observable<ProjectoResponse>{
    const  endpoint = `takina/proyectos/id/${proyectoId}`;
    return this.http.get<ProjectoResponse>(endpoint);
  }

  updateProjectoById(){
    


  }
}