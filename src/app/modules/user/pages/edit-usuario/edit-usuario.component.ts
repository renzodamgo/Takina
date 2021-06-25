import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Location } from '@angular/common';
import { Usuario,EditUsuario } from 'src/app/models/usuario';
import { DataService } from 'src/app/core/services/data.service';
import { UsuarioService } from 'src/app/core/services/usuario.service';

@Component({
	selector: 'app-edit-usuario',
	templateUrl: './edit-usuario.component.html',
	styleUrls: ['./edit-usuario.component.css']
})
export class EditUsuarioComponent implements OnInit {
	editUsuario: EditUsuario = new EditUsuario(
		'','','',-1,'',''
	);

	public usuario:Usuario = new Usuario(
		'','',this.dataService.defaultFotoPerfil,-1,'','',false,''
	);

	constructor(
		private location: Location,
		private usuarioService:UsuarioService,
		private _snackBar: MatSnackBar,
		private dataService: DataService,
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
				this.editUsuario.id = this.usuario.id;
				this.editUsuario.nombre = this.usuario.nombre;
				this.editUsuario.apodo = this.usuario.apodo;
				this.editUsuario.correo = this.usuario.correo;
				this.editUsuario.password = this.usuario.password;
				this.editUsuario.fotoPerfil = this.usuario.fotoPerfil;
			},(errorServicio)=>{
				console.log(errorServicio.error)
				this.regresar();
			  }
			);
	}

	editarUsuario() {
		this.usuarioService.editUsuario(this.editUsuario)
			.subscribe(data => {
				this._snackBar.open("Usuario correctamente editado.", "OK" );
				this.regresar();
		});
	}

}
