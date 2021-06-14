import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Artista } from './dashboard/artista';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ArtistaService {

  constructor(private http:HttpClient) { }

  getArtista():Observable<Artista>{
    const  endpoint = 'takina/artistas/id/1';
    return this.http.get<Artista>(endpoint);
  }
}
