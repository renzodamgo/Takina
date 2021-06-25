import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  artistaId!: number;
  usuarioId!: number;
  defaultFotoPerfil:string = 'http://brownmead.academy/wp-content/uploads/2017/01/avatar.jpg';
  constructor() { }
  
}
