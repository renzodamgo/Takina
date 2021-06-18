import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { ProjectoResponse  } from './models/proyectResponse';


@Injectable({
  providedIn: 'root'
})
export class ProyectoService {

  constructor(private http:HttpClient) { }

  getProyectos(artistaId:number):Observable<ProjectoResponse>{
    const  endpoint = `takina/proyectos/artista/${artistaId}`;
    return this.http.get<ProjectoResponse>(endpoint);
  }

  getProyectoById(proyectoId:number):Observable<ProjectoResponse>{
    const  endpoint = `takina/proyectos/id/${proyectoId}`;
    return this.http.get<ProjectoResponse>(endpoint);
  }

  updateProjectoById(){
    


  }
}
