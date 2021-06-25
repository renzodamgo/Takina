import { Component, OnInit } from '@angular/core';
import { UsuarioService } from 'src/app/core/services/usuario.service';
import { DataService } from 'src/app/core/services/data.service';
import { Usuario } from 'src/app/models/usuario';

@Component({
	selector: 'app-user-dashboard',
	templateUrl: './user-dashboard.component.html',
	styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit {
	public usuario:Usuario = new Usuario(
		'','',this.dataService.defaultFotoPerfil,0,'','',false,''
	);

	constructor(
		private usuarioService:UsuarioService,
		private dataService:DataService
	) { }

	ngOnInit(): void {
		this.getUser();
	}

	getUser(){
		this.usuarioService.getUsuarioById(this.dataService.usuarioId)
			.subscribe(usuario =>{
				this.usuario = usuario;
			});
	}

}
