import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Location } from '@angular/common';
import { Usuario, EditUsuario } from 'src/app/models/usuario';
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
				this.editUsuario = EditUsuario.editUsuarioFromUsuario(this.usuario);
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
