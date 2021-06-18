import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Projecto  } from './proyects/proyect';

@Injectable({
  providedIn: 'root'
})
export class ProyectoService {

  constructor(private http:HttpClient) { }

  getProyectos(artistaId:number):Observable<Projecto>{
    const  endpoint = `takina/proyectos/artista/${artistaId}`;
    return this.http.get<Projecto>(endpoint);
  }

  getProyectoById(proyectoId:number):Observable<Projecto>{
    const  endpoint = `takina/proyectos/id/${proyectoId}`;
    return this.http.get<Projecto>(endpoint);
  }

  updateProjectoById(){
    


  }
}
