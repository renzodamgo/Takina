import { Component, OnInit } from '@angular/core';
import { ProyectoService } from 'src/app/core/services/proyecto.service';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Canciones, Proyecto } from 'src/app/models/projecto';

@Component({
	selector: 'app-project-details',
	templateUrl: './project-details.component.html',
	styleUrls: ['./project-details.component.css']
})
export class ProjectDetailsComponent implements OnInit {

	proyecto!: Proyecto;
	canciones: Canciones[] = [];
	id!: number;

	constructor(
		private location: Location,
		private proyectoService:ProyectoService,
		private route:ActivatedRoute,
		private _snackBar: MatSnackBar
	) { }

	ngOnInit(): void {
		this.getProject();
	}

	goBack() {
		this.location.back();
	}

	getProject(){
		this.id  = +this.route.snapshot.paramMap.get('id')!;
		this.proyectoService.getProyectoById(this.id)
		  .subscribe(result => {
			this.proyecto = result;
			this.canciones = result.canciones;
			console.log("PROYECTO",this.proyecto);
			console.log("CANCIONES",this.canciones);
		})
	}
}
