import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Location } from '@angular/common';
import { Artista, EditArtista } from 'src/app/models/artista';
import { DataService } from 'src/app/core/services/data.service';
import { ArtistaService } from 'src/app/core/services/artista.service';

@Component({
	selector: 'app-edit-artista',
	templateUrl: './edit-artista.component.html',
	styleUrls: ['./edit-artista.component.css']
})
export class EditArtistaComponent implements OnInit {
	public editArtista: EditArtista = new EditArtista(
		'','','','','',0,''
	);

	public artista:Artista = new Artista(
		'','','','','',0,'',0,0
	);


	constructor(
		private location: Location,
		private artistaService:ArtistaService,
		private _snackBar: MatSnackBar,
		private dataService: DataService,
	) { }

	ngOnInit(): void {
		this.getArtista();
	}

	regresar(){
		this.location.back();
	}

	getArtista(){
		this.artistaService.getArtistaById(this.dataService.artistaId)
			.subscribe(artista =>{
				this.artista = artista;
				this.editArtista = EditArtista.editArtistaFromArtista(this.artista);
			},(errorServicio)=>{
				console.log(errorServicio.error)
				this.regresar();
			  }
			);
	}

	editarArtista() {
		this.artistaService.editArtista(this.editArtista)
			.subscribe(data => {
				this._snackBar.open("Artista correctamente editado.", "OK" );
				this.regresar();
			})
	}

}
