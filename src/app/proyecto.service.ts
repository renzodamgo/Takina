import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Proyect } from './proyects/proyect';

@Injectable({
  providedIn: 'root'
})
export class ProyectoService {

  constructor(private http:HttpClient) { }

  getProyectos(artistaId:number):Observable<Proyect>{
    const  endpoint = `takina/proyectos/artista/${artistaId}`;
    return this.http.get<Proyect>(endpoint);
  }
}
