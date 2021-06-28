import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { CancionService } from 'src/app/core/services/cancion.service';
import { PlaylistService } from 'src/app/core/services/playlist.service';
import { DataService } from 'src/app/core/services/data.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UsuarioService } from 'src/app/core/services/usuario.service';
import { Playlist } from 'src/app/models/playlist';
import { Usuario } from 'src/app/models/usuario';
import { Cancion } from 'src/app/models/cancion';
import { ArtistaService } from 'src/app/core/services/artista.service';
import { ProyectoService } from 'src/app/core/services/proyecto.service';

@Component({
	selector: 'app-show-playlist',
	templateUrl: './show-playlist.component.html',
	styleUrls: ['./show-playlist.component.css']
})
export class ShowPlaylistComponent implements OnInit {
	displayedColumns: string[] = ['artista','nombre', 'duracion', 'reproducir' ,'eliminar'];
	usuario!: Usuario;
	playlistId!: number;
	thePlaylist!: Playlist;
	canciones: Cancion[] = [];
	artistaInfo: { [proyectoId:string]:string } = {};

	constructor(
		private location: Location,
		private cancionService:CancionService,
		private usuarioService:UsuarioService,
		private playlistService:PlaylistService,
		private dataService:DataService,
		private artistaService:ArtistaService,
		private proyectoService:ProyectoService,
		private route:ActivatedRoute,
		private _snackBar: MatSnackBar,
		private router:Router
	) { }

	ngOnInit(): void {
		this.getUser();
		this.getPlaylistAndCanciones();
	}

	getDuration(duration: number){
		return Cancion.durationFromFloat(duration);
	}

	goBack() {
		this.location.back();
	}

	getArtistaNombreByCancionId(cancion:Cancion){
		let str: string = cancion.proyectoId.toString();
		return this.artistaInfo[str] || "";
	}

	playSong(cancion:Cancion){
		this.cancionService.playCancion(this.usuario.id,cancion.id)
			.subscribe(result => {
				this._snackBar.open(`Reproduciendo ${cancion.nombre}...`,"OK");
			})
	}

	deleteSong(cancion:Cancion){
		let temp:string = cancion.nombre;
		this.playlistService.deleteFromPlaylistByIdAndCancionId(this.thePlaylist.id,cancion.id)
			.subscribe(result => {
				this.cancionService.getCancionesByPlaylistId(this.thePlaylist.id)
				.subscribe(results => {
					this.canciones = results;
				})
				this._snackBar.open(`${temp} eliminada de la playlist`,"OK");
			});
	}

	getUser(){
		this.usuarioService.getUsuarioById(this.dataService.usuarioId)
			.subscribe(usuario =>{
				this.usuario = usuario;
			},(errorServicio)=>{
				console.log(errorServicio.error)
				this.router.navigate(['../../../auth/login']);
			}
		);
	}

	getPlaylistAndCanciones(){
		this.playlistId  = +this.route.snapshot.paramMap.get('id')!;
		this.playlistService.getPlaylistById(this.playlistId)
			.subscribe(result => {
				this.thePlaylist = result;
				this.cancionService.getCancionesByPlaylistId(this.thePlaylist.id)
					.subscribe(results => {
						this.canciones = results;
						this.canciones.forEach((cancion) => {
							this.proyectoService.getProyectoById(cancion.proyectoId)
								.subscribe(proy => {
									let idChar: string = proy.id.toString();
									if (!(idChar in this.artistaInfo)){
										this.artistaService.getArtistaById(proy.artistaId)
											.subscribe(artista =>{
												this.artistaInfo[idChar] = artista.nombre;
											}
										);
									}
								})
							});
						})
					})
				}
}
