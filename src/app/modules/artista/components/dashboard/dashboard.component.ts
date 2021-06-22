import { Component, OnInit } from '@angular/core';
import { ArtistaService } from '../../../../core/services/artista.service';
import { Artista } from '../../../../models/artista';
import { DataService } from '../../../../core/services/data.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  
  public artista!:Artista;
  public fotoPerfil:string = 'http://brownmead.academy/wp-content/uploads/2017/01/avatar.jpg'


  constructor(private artistaService:ArtistaService,
              private dataService:DataService) { }

  ngOnInit(): void {
     this.getArtista()
  }

  getArtista(){
    this.artistaService.getArtista()
      .subscribe(artista =>{
        // this.artista = response.data;
        console.log(artista)
        this.artista = artista;
        this.dataService.artistaId = artista.id;
        this.dataService.fotoPerfilUrl = artista.fotoPerfil;
        this.fotoPerfil = artista.fotoPerfil;
      });
  }

 
  

}
