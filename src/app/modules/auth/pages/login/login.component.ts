import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataService } from 'src/app/core/services/data.service';
import { UsuarioService } from 'src/app/core/services/usuario.service';
import { Usuario, LoginUsuario } from 'src/app/models/usuario';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

	loginUsuario: LoginUsuario = new LoginUsuario(
		'',''
	);

	constructor(
		private usuarioService:UsuarioService,
		private dataService:DataService,
		private router:Router,
		private _snackBar:MatSnackBar
	) { }

	ngOnInit(): void {
	}

	loginUser(){
		if (this.loginUsuario.login == "" ||
			this.loginUsuario.password == ""){
				this._snackBar.open("No dejes campos vacios!", "OK" );
				return
			}

		this.usuarioService.loginUsuario(this.loginUsuario)
		.subscribe( usuario => {
			this.dataService.usuarioId = usuario.id;
			this._snackBar.open(`Bienvenid@ de vuelta, ${usuario.nombre}!`, "OK" );
			this.router.navigate(['../usuario/dashboard']);
		}, (errorServicio)=> {
			console.log(errorServicio.error.error);
			this._snackBar.open("Datos invalidos!","OK");
		}
	);
  }

}
