import { Component, OnInit } from '@angular/core';
import { ArtistaService } from 'src/app/core/services/artista.service';
import { DataService } from 'src/app/core/services/data.service';
import { ProyectoService } from 'src/app/core/services/proyecto.service';
import { Proyecto } from 'src/app/models/projecto';

@Component({
  selector: 'app-show-projects',
  templateUrl: './show-projects.component.html',
  styleUrls: ['./show-projects.component.css']
})
export class ShowProjectsComponent implements OnInit {
	public proyectos:Proyecto[] = [];
	artistaInfo: { [artistaId: string]: string[] } = {};

	constructor(
		public dataService:DataService,
		public artistaService:ArtistaService,
		public proyectoService:ProyectoService
	) { }
	
	ngOnInit(): void {
		this.getProyectosInitial();	
	}

	infoEmpty(obj:any) {
		return Object.keys(obj).length === 0;
	  }

	getProyectosInitial(){
		this.proyectoService.getUltimosProyectos()
			.subscribe(result => {
				this.proyectos = result;

				// Fill dictionary
				this.proyectos.forEach((proyecto) => {
					let idChar: string = proyecto.artistaId.toString();
					if (!(idChar in this.artistaInfo)){
						this.artistaService.getArtistaById(proyecto.artistaId)
							.subscribe(artista =>{
								this.artistaInfo[idChar] = [artista.nombre,artista.fotoPerfil];
						});
					}
				});
			}
		)
	}

	getArtistaName(artistaId: number){
		let str: string = artistaId.toString();
		return this.artistaInfo[str][0] || "";
	}

	getArtistaAvatar(artistaId: number){
		let str: string = artistaId.toString();
		return this.artistaInfo[str][1] || this.dataService.defaultFotoPerfil;
	}

}
