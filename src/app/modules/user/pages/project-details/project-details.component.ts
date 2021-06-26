import { Component, OnInit, ViewChild } from '@angular/core';
import { ProyectoService } from 'src/app/core/services/proyecto.service';
import { Location } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Proyecto } from 'src/app/models/projecto';
import { CancionService } from 'src/app/core/services/cancion.service';
import { UsuarioService } from 'src/app/core/services/usuario.service';
import { DataService } from 'src/app/core/services/data.service';
import { Usuario } from 'src/app/models/usuario';
import { Cancion } from 'src/app/models/cancion';
import { Playlist } from 'src/app/models/playlist';
import { PlaylistService } from 'src/app/core/services/playlist.service';

@Component({
	selector: 'app-project-details',
	templateUrl: './project-details.component.html',
	styleUrls: ['./project-details.component.css']
})
export class ProjectDetailsComponent implements OnInit {
	displayedColumns: string[] = ['track', 'nombre', 'duracion', 'reproducir','añadir'];
	
	usuario!: Usuario;
	proyecto!: Proyecto;
	canciones: Cancion[] = [];
	cancion!: Cancion;
	proyectoId!: number;
	userPlaylists: Playlist[] = [];

	constructor(
		private location: Location,
		private proyectoService:ProyectoService,
		private cancionService:CancionService,
		private usuarioService:UsuarioService,
		private playlistService:PlaylistService,
		private dataService:DataService,
		private route:ActivatedRoute,
		private _snackBar: MatSnackBar,
		private router:Router
	) { }

	ngOnInit(): void {
		this.getUserAndPlaylists();
		this.getProjectAndCanciones();
	}

	getDuration(duration: number){
		return Cancion.durationFromFloat(duration);
	}

	goBack() {
		this.location.back();
	}

	getProjectAndCanciones(){
		this.proyectoId  = +this.route.snapshot.paramMap.get('id')!;
		this.proyectoService.getProyectoById(this.proyectoId)
		  .subscribe(result => {
			this.proyecto = result;
			this.cancionService.getCancionesByProyectoId(this.proyecto.id)
				.subscribe(result => {
					this.canciones = result;
				})
		})
	}

	getUserAndPlaylists(){
		this.usuarioService.getUsuarioById(this.dataService.usuarioId)
			.subscribe(usuario =>{
				this.usuario = usuario;
				this.playlistService.getPlaylistsByUsuarioId(this.usuario.id)
					.subscribe(result => {
						this.userPlaylists = result;
					})
			},(errorServicio)=>{
				console.log(errorServicio.error)
				this.router.navigate(['../../../auth/login']);
			}
		);
	}

	playSong(cancion:Cancion){
		this.cancionService.playCancion(this.usuario.id,cancion.id)
			.subscribe(result => {
				this._snackBar.open(`Reproduciendo ${cancion.nombre}...`,"OK");
			})
	}

	setCancion(cancion:Cancion) {
		this.cancion = cancion;
	}

	addToPlaylist(playlist:Playlist) {
		this.playlistService.addToPlaylistByIdAndCancionId(playlist.id,this.cancion.id)
			.subscribe(result => {
				this._snackBar.open(`${this.cancion.nombre} añadida a ${playlist.nombre}`,"OK");
			})
	}

	
}
