import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  artistaId: number = 1;
  fotoPerfilUrl:string = '';
  constructor() { }
}
