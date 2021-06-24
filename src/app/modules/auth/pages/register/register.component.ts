import { Component, OnInit } from '@angular/core';
import { CreateUsuario } from 'src/app/models/usuario';
import { Router } from '@angular/router';
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
		private router:Router,
		private _snackBar:MatSnackBar
 	) { }


 	ngOnInit(): void {
 	}

	registerUsuario() {

		if (this.createUsuario.apodo == "" ||
			this.createUsuario.correo == "" ||
			this.createUsuario.password == "" ||
			this.createUsuario.nombre == "" ||
			this.createUsuario.fotoPerfil == ""){
				this._snackBar.open("Llene todos los campos por favor", "OK" );
				return
			}

		this.usuarioService.createUsuario(this.createUsuario)
			.subscribe( usuario => {
				this._snackBar.open(`Usuario ${usuario.apodo} creado correctamente`, "OK" );
				this.regresar();
			}, (errorServicio)=> {
				this._snackBar.open("Usuario no ha podido ser registrado", "OK" );
				console.log(errorServicio.error.error);
			}
		);

	}

	regresar() {
		this.router.navigate(['auth/login']);
	}

}
