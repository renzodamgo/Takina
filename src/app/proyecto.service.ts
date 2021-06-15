import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Data  } from './proyects/proyect';

@Injectable({
  providedIn: 'root'
})
export class ProyectoService {

  constructor(private http:HttpClient) { }

  getProyectos(artistaId:number):Observable<Data>{
    const  endpoint = `takina/proyectos/artista/${artistaId}`;
    return this.http.get<Data>(endpoint);
  }

  getProyectoById(proyectoId:number):Observable<Data>{
    const  endpoint = `takina/proyectos/id/${proyectoId}`;
    return this.http.get<Data>(endpoint);
  }

  updateProjectoById(){
    


  }
}
