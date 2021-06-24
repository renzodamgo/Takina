import { Component, OnInit } from '@angular/core';
import { CreateUsuario } from 'src/app/models/usuario';
import { Router } from '@angular/router';
import { DataService } from 'src/app/core/services/data.service';
import { UsuarioService } from 'src/app/core/services/usuario.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
	selector: 'app-register',
	templateUrl: './register.component.html',
	styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

 	createUsuario: CreateUsuario = new CreateUsuario(
		'','','','',''
 	);

 	constructor(
		private usuarioService:UsuarioService,
		private dataService:DataService,
		private router:Router,
 	) { }


 	ngOnInit(): void {
 	}

	registerUsuario() {
		this.usuarioService.createUsuario(this.createUsuario)
		.subscribe( usuario => {
			console.log(usuario);
			this.regresar();
		}, (errorServicio)=> {
			console.log(errorServicio.error);
			console.log(errorServicio.error.error);
		}
		);

	}

	regresar() {
		this.router.navigate(['auth/login']);
	}

}
