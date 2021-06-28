import { Component, OnInit } from '@angular/core';
import { ArtistaService } from 'src/app/core/services/artista.service';
import { DataService } from 'src/app/core/services/data.service';
import { UsuarioService } from 'src/app/core/services/usuario.service';
import { CreateArtista } from 'src/app/models/artista';
import { Usuario } from 'src/app/models/usuario';
import { Location } from '@angular/common';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
	selector: 'app-create-artista',
	templateUrl: './create-artista.component.html',
	styleUrls: ['./create-artista.component.css']
})
export class CreateArtistaComponent implements OnInit {

	usuario!: Usuario;
	createArtista: CreateArtista = new CreateArtista(
		'','','','','','',-1
	);

	constructor(
		private location: Location,
		private _snackBar: MatSnackBar,
		private usuarioService:UsuarioService,
		private artistaService:ArtistaService,
		public dataService:DataService
	) { }

	ngOnInit(): void {
		this.getUser();
	}

	regresar(){
		this.location.back();
	}

	getUser(){
		this.usuarioService.getUsuarioById(this.dataService.usuarioId)
			.subscribe(usuario =>{
				this.usuario = usuario;
			},(errorServicio)=>{
				console.log(errorServicio.error)
				this.regresar();
			  }
		);
	}

	newArtista() {
		this.createArtista.usuarioId = this.usuario.id;
		this.createArtista.fotoPortada = this.createArtista.fotoPerfil;
		this.artistaService.createArtistaByUserId(this.createArtista)
			.subscribe(res => {
				this._snackBar.open("Artista creado correctamente!","OK");
				this.regresar();
			},(errorServicio)=>{
				this._snackBar.open("No deje campos vacios!", "OK" );
				console.log(errorServicio.error.error);
			}
			)
	}

}
