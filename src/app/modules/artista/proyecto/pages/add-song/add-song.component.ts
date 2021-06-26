import { Component, OnInit } from '@angular/core';
import { Canciones, Proyecto } from 'src/app/models/projecto';
import { ActivatedRoute } from '@angular/router';
import { Cancion, CreateCancion } from 'src/app/models/cancion';
import { CancionService } from 'src/app/core/services/cancion.service';
import { Location } from '@angular/common';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-add-song',
  templateUrl: './add-song.component.html',
  styleUrls: ['./add-song.component.css']
})


export class AddSongComponent implements OnInit {

	displayedColumns: string[] = ['track', 'nombre', 'duracion', 'eliminar'];
	
	canciones:Canciones[] = [];
	nombre:string = "";
	proyectoId!:number;

	createCancion:CreateCancion = new CreateCancion(
		"",-1,"",-1
	);

	getDuration(duration: number){
		return Cancion.durationFromFloat(duration);
	}

	constructor(
		private cancionService:CancionService,
		private route:ActivatedRoute,
		private location: Location,
		private _snackBar:MatSnackBar
	) { }

	ngOnInit(): void {
		this.getCancionesByProyectoId();
	}

	goBack() {
		this.location.back();
	}

	getCancionesByProyectoId(){
		this.proyectoId = +this.route.snapshot.paramMap.get('id')!;
		this.cancionService.getCancionesByProyectoId(this.proyectoId)
			.subscribe(result => {
				this.canciones = result;
			})
	}

	addSongByProyectId(){
		this.proyectoId = +this.route.snapshot.paramMap.get('id')!;
		this.createCancion.proyectoId = this.proyectoId;
		this.cancionService.addCancion(this.createCancion)
			.subscribe(data => {
				this.getCancionesByProyectoId();
			})
	}

	deleteSongByCancionId(cancionId: number){
		console.log(cancionId);
		this.cancionService.deleteCancion(cancionId)
			.subscribe( result =>{
				this.getCancionesByProyectoId();
				this._snackBar.open("La cancion ha sido eliminada","OK");
			})
	}
}
