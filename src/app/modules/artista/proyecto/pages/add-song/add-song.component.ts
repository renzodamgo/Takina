import { Component, OnInit } from '@angular/core';
import { ProyectoService } from 'src/app/core/services/proyecto.service';
import { Canciones, Proyecto } from 'src/app/models/projecto';
import { ActivatedRoute } from '@angular/router';
import { Cancion, CreateCancion } from 'src/app/models/cancion';
import { CancionService } from 'src/app/core/services/cancion.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-add-song',
  templateUrl: './add-song.component.html',
  styleUrls: ['./add-song.component.css']
})


export class AddSongComponent implements OnInit {

	displayedColumns: string[] = ['track', 'nombre', 'duracion'];
	
	project!:Proyecto;
	canciones:Canciones[] = [];
	nombre:string = "";
	proyectoId!:number;

	cancion:Cancion ={
		audio: "",
		duracion: 0,
		fotoPortada: "",
		genero: "",
		id: 0,
		lanzamiento: "",
		nombre: "",
		proyectoId: 0,
		track: 0,
	};

	createCancion:CreateCancion = new CreateCancion(
		"",-1,"",-1
	);
	//listaCanciones:Cancion[] = []

	getDuration(duration: number){
		return Cancion.durationFromFloat(duration);
	}

	constructor(
		private proyectoService:ProyectoService,
		private cancionService:CancionService,
		private route:ActivatedRoute,
		private location: Location,
	) { }

	ngOnInit(): void {
		//this.getProject();
		this.getCancionesByProyectoId();
	}

	goBack() {
		this.location.back();
	}



	getProject(){
	  this.proyectoId  = +this.route.snapshot.paramMap.get('id')!;
	  this.proyectoService.getProyectoById(this.proyectoId)
	    .subscribe((result)=>{
	    	this.project = result;
	    	this.canciones = result.canciones;
	    	this.nombre = result.nombre;
	    	console.log(this.project);
	    	console.log(this.canciones);
	    	console.log(this.proyectoId);
	    	this.cancion.proyectoId = this.proyectoId;


	    })

	}
	getCancionesByProyectoId(){
		this.proyectoId = +this.route.snapshot.paramMap.get('id')!;
		this.cancionService.getCancionesByProyectoId(this.proyectoId)
			.subscribe(result => {
				this.canciones = result;
			})
	}

	AddSong(){
		console.log(this.cancion);
		this.proyectoService.addCancionByProjectoId(this.cancion)
			.subscribe(data => {
			  console.log(this.cancion);
			  console.log(data);
			  this.getProject()
		});
	}
}
