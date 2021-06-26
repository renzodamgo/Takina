import { Component, OnInit } from '@angular/core';
import { ProyectoService } from 'src/app/core/services/proyecto.service';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Proyecto } from 'src/app/models/projecto';
import { CancionService } from 'src/app/core/services/cancion.service';
import { UsuarioService } from 'src/app/core/services/usuario.service';
import { DataService } from 'src/app/core/services/data.service';
import { Usuario } from 'src/app/models/usuario';
import { Cancion } from 'src/app/models/cancion';

@Component({
	selector: 'app-project-details',
	templateUrl: './project-details.component.html',
	styleUrls: ['./project-details.component.css']
})
export class ProjectDetailsComponent implements OnInit {
	displayedColumns: string[] = ['track', 'nombre', 'duracion', 'eliminar'];
	usuario!: Usuario;
	proyecto!: Proyecto;
	canciones: Cancion[] = [];
	proyectoId!: number;

	constructor(
		private location: Location,
		private proyectoService:ProyectoService,
		private cancionService:CancionService,
		private usuarioService:UsuarioService,
		private dataService:DataService,
		private route:ActivatedRoute,
		private _snackBar: MatSnackBar
	) { }

	ngOnInit(): void {
		this.getProjectAndCanciones();
	}

	getDuration(duration: number){
		return Cancion.durationFromFloat(duration);
	}

	goBack() {
		this.location.back();
	}

	getUser(){
		this.usuarioService.getUsuarioById(this.dataService.usuarioId)
			.subscribe(usuario =>{
				this.usuario = usuario;
			},(errorServicio)=>{
				console.log(errorServicio.error)
				//this.router.navigate(['../auth/login']);
			}
		);
	}

	getProjectAndCanciones(){
		this.proyectoId  = +this.route.snapshot.paramMap.get('id')!;
		this.proyectoService.getProyectoById(this.proyectoId)
		  .subscribe(result => {
			this.proyecto = result;
			this.cancionService.getCancionesByProyectoId(this.proyecto.id)
				.subscribe(result => {
					this.canciones = result;
					console.log(this.canciones);
				})
		})
	}

	

	
}
