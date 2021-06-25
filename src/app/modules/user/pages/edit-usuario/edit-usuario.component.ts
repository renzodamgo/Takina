import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ProyectoService } from 'src/app/core/services/proyecto.service';

@Component({
	selector: 'app-edit-usuario',
	templateUrl: './edit-usuario.component.html',
	styleUrls: ['./edit-usuario.component.css']
})
export class EditUsuarioComponent implements OnInit {

	
	constructor(
		private location: Location,
		private proyectoService:ProyectoService,
		private _snackBar: MatSnackBar
	) { }

	ngOnInit(): void {
	}

}
