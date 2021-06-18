import { Component, OnInit } from '@angular/core';
import { ArtistaService } from '../artista.service';
import { Artista } from '../models/artista';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  
  public artista!:Artista;


  constructor(private artistaService:ArtistaService) { }

  ngOnInit(): void {
     this.getArtista()
  }

  getArtista(){
    this.artistaService.getArtista()
      .subscribe(artista =>{
        // this.artista = response.data;
        console.log(artista)
        this.artista = artista;

      });
  }

 
  

}
