import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DataService } from 'src/app/core/services/data.service';
import { ProyectoService } from 'src/app/core/services/proyecto.service';
import { Proyecto } from 'src/app/models/projecto';
import { UsuarioService } from 'src/app/core/services/usuario.service';
import { Usuario } from 'src/app/models/usuario';


@Component({
	selector: 'app-search-bar',
	templateUrl: './search-bar.component.html',
	styleUrls: ['./search-bar.component.css']
})
export class SearchBarComponent implements OnInit {
	public categoria: string = ""; 
	public categoriaNueva: string = "todos";
	
	public busquedaNueva: string = "";

	public usuario!: Usuario;

	@Output() cambioCategoria = new EventEmitter<string>();

	constructor(
		private router: Router,
		private _snackBar:MatSnackBar,
		private dataService:DataService,
		private proyectoService:ProyectoService,
		private usuarioService:UsuarioService
	) { }

	ngOnInit(): void {
		this.getUser();
	}

	onSubmit(form: NgForm) {
		if (!this.categoria) {
			this._snackBar.open("Selecciona una categoria!", "OK");
			return;
		}
		this.categoriaNueva = this.categoria;
		this.busquedaNueva = form.value.search;
		
		//this.cambioCategoria.emit(this.categoria);
		//this.dataService.categoria = this.categoria;
		
	}

	getUser(){
		this.usuarioService.getUsuarioById(this.dataService.usuarioId)
			.subscribe(usuario =>{
				this.usuario = usuario;
			},(errorServicio)=>{
				console.log(errorServicio.error)
				this.router.navigate(['../auth/login']);
			}
		);
	}
}