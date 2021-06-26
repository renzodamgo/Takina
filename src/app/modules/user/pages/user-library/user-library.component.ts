import { Component, OnInit } from '@angular/core';
import { DataService } from 'src/app/core/services/data.service';
import { PlaylistService } from 'src/app/core/services/playlist.service';
import { UsuarioService } from 'src/app/core/services/usuario.service';
import { Playlist } from 'src/app/models/playlist';
import { Location } from '@angular/common';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Usuario } from 'src/app/models/usuario';

@Component({
  selector: 'app-user-library',
  templateUrl: './user-library.component.html',
  styleUrls: ['./user-library.component.css']
})
export class UserLibraryComponent implements OnInit {

	userPlaylists: Playlist[] = [];
	usuario!: Usuario; 

	constructor(
		private location: Location,
		private usuarioService:UsuarioService,
		private playlistService:PlaylistService,
		private dataService:DataService,
		private _snackBar: MatSnackBar,
		private router:Router
	) { }

	ngOnInit(): void {
		this.getUserAndPlaylists();
	}

	goBack() {
		this.location.back();
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

	deletePlaylist(playlist:Playlist){
		let temp: string = playlist.nombre;
		this.playlistService.deletePlaylistById(playlist.id)
			.subscribe(response =>{
				this.playlistService.getPlaylistsByUsuarioId(this.usuario.id)
					.subscribe(result => {
						this.userPlaylists = result;
					})
				this._snackBar.open(`Playlist ${temp} eliminada correctamente.`,"OK")
			})

	}

}
