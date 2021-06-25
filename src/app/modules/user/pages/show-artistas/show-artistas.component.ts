import { Component, OnInit } from '@angular/core';

import { Artista } from 'src/app/models/artista';
import { ArtistaService } from 'src/app/core/services/artista.service';
import { Usuario } from 'src/app/models/usuario';
import { DataService } from 'src/app/core/services/data.service';
import { UsuarioService } from 'src/app/core/services/usuario.service';
import { Router } from '@angular/router';

@Component({
	selector: 'app-show-artistas',
	templateUrl: './show-artistas.component.html',
	styleUrls: ['./show-artistas.component.css']
})
export class ShowArtistasComponent implements OnInit {
	public usuario!:Usuario;
	public artistas:Artista[] = [];

	constructor(
		private usuarioService:UsuarioService,
		private artistaService: ArtistaService,
	    public dataService:DataService,
		private router:Router,
	) { }

	ngOnInit(): void {
		this.getUser();
		this.getArtistas();
	}

	getUser(){
		this.usuarioService.getUsuarioById(this.dataService.usuarioId)
			.subscribe(usuario =>{
				this.usuario = usuario;
				console.log("BASSURA",this.usuario.id);
				//this.goToAuth();
			},(errorServicio)=>{
				console.log(errorServicio.error)
				this.router.navigate(['../auth/login']);
			  }
			);
	}

	getArtistas(){
		this.artistaService.getArtistasByUserId(this.dataService.usuarioId)
			.subscribe(result => {
				this.artistas = result;
				console.log(this.artistas)
		});
	}

	administrateArtista(artistaId: number){
		this.dataService.artistaId = artistaId;
		this.router.navigate(['../artista/dashboard']);
	}

}