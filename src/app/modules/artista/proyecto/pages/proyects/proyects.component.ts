import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

import { Proyecto } from '../../../../../models/projecto';
import { ProyectoService } from '../../../../../core/services/proyecto.service';
import { DataService } from '../../../../../core/services/data.service';
import { Artista } from 'src/app/models/artista';
import { ArtistaService } from 'src/app/core/services/artista.service';

@Component({
	selector: 'app-proyects',
	templateUrl: './proyects.component.html',
	styleUrls: ['./proyects.component.css'],
})
export class ProyectsComponent implements OnInit {
	
	public artista!:Artista;
	public fotoPerfil!:string;
	public proyectos:Proyecto[] = [];
	

	constructor(
		private proyectoService: ProyectoService,
		private artistaService: ArtistaService,
	    public dataService:DataService,
	    private _snackBar:MatSnackBar
	 ){ }

	ngOnInit(): void {
		this.getArtista();
		this.getProyectos();
		if (this.artista.fotoPerfil == '') {
			this.fotoPerfil = this.dataService.defaultFotoPerfil;
		}
	}

	deleteSnackBar() {
	  this._snackBar.open("El proyecto ha sido eliminado", "OK" );
	}

	getArtista(){
		this.artistaService.getArtistaById(this.dataService.artistaId)
		  .subscribe(artista =>{
			this.artista = artista;
			this.fotoPerfil = artista.fotoPerfil;
		  });
	  }

	getProyectos(){
		this.proyectoService.getProyectos(this.dataService.artistaId)
			.subscribe(result=>{
				this.proyectos= result;
				}
			)
	}

	deleteProyecto(id: number) {
		this.proyectoService.deleteProyectoById(id)
		.subscribe( result => {
			// No es necesario usar navigate, usa un get para que se actualizen los datos y angular lo renderiza real-time wow
			// Si quisieras usar el navigate sería (['']) porque ya estas dentro de (/proyecto) 
			// this.router.navigate(['/proyectos']); aqui estarías entrando a /proyecto/proyectos
			// y yo puse en el routing que cuando entras a un route q no existe te manda al dashboard de artista con el ['**']
			this.getProyectos();
			this.deleteSnackBar()
		});
	}

}
