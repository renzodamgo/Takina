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
  public fotoPerfil!:string;

  constructor(private artistaService:ArtistaService,
              private dataService:DataService) { }

  ngOnInit(): void {
    this.getArtista();
	if (this.artista.fotoPerfil == '') {
		this.fotoPerfil = this.dataService.defaultFotoPerfil;
	}
  }

  getArtista(){
    this.artistaService.getArtistaById(this.dataService.artistaId)
      .subscribe(artista =>{
        this.artista = artista;
        this.fotoPerfil = artista.fotoPerfil;
      });
  }

}
