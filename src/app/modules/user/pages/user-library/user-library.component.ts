import { Component, Inject, OnInit } from '@angular/core';
import { DataService } from 'src/app/core/services/data.service';
import { PlaylistService } from 'src/app/core/services/playlist.service';
import { UsuarioService } from 'src/app/core/services/usuario.service';
import { CreatePlaylist, EditPlaylist, Playlist } from 'src/app/models/playlist';
import { Location } from '@angular/common';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Usuario } from 'src/app/models/usuario';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-user-library',
  templateUrl: './user-library.component.html',
  styleUrls: ['./user-library.component.css']
})
export class UserLibraryComponent implements OnInit {

	userPlaylists: Playlist[] = [];
	usuario!: Usuario; 
	createPlaylist:CreatePlaylist = new CreatePlaylist(
		"","",0
	);

	editPlaylist:EditPlaylist = new EditPlaylist(
		"","",0
	);

	constructor(
		private location: Location,
		private usuarioService:UsuarioService,
		private playlistService:PlaylistService,
		private dataService:DataService,
		private _snackBar: MatSnackBar,
		private router:Router,
		public dialog: MatDialog
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

	openDialog(accion: number): void {
		if (accion == -1) {
			const dialogRef = this.dialog.open(PopUpDialog, {
				width: '250px',
				data: {
					accion: accion,
					nombre: "",
					descripcion: ""
				}
			});
	
			dialogRef.afterClosed().subscribe(result => {
				console.log(result);
				this._snackBar.open(`Playlist ${result.nombre} ha sido creada!`, "OK" );
				this.createPlaylist.usuarioId = this.usuario.id;
				this.createPlaylist.nombre = result.nombre;
				this.createPlaylist.descripcion = result.descripcion;
				this.playlistService.createPlaylistByUserId(this.createPlaylist)
					.subscribe(res => {
						this.playlistService.getPlaylistsByUsuarioId(this.usuario.id)
							.subscribe(result => {
								this.userPlaylists = result;
							})
					})
	
			});

			
		}

		if (accion > 0) {
			let temp:Playlist = this.userPlaylists[accion];
			const dialogRef = this.dialog.open(PopUpDialog, {
				width: '250px',
				data: {
					accion: accion,
					nombre: temp.nombre,
					descripcion: temp.descripcion
				}
			});
			dialogRef.afterClosed().subscribe(result => {
				this.editPlaylist.id = temp.id;
				this.editPlaylist.nombre = result.nombre;
				this.editPlaylist.descripcion = result.descripcion;
				this.playlistService.editPlaylistById(this.editPlaylist)
					.subscribe(res => {
						this.playlistService.getPlaylistsByUsuarioId(this.usuario.id)
							.subscribe(result => {
								this.userPlaylists = result;
							})
					})
			});
		}
	}
}

export interface DialogData {
	accion: number;
	nombre: string;
	descripcion: string;
}
@Component({
	selector: 'new-playlist-dialog',
	templateUrl: './new-playlist-dialog.html',
  })
export class PopUpDialog {
	constructor(
		public dialogRef: MatDialogRef<PopUpDialog>,
		@Inject(MAT_DIALOG_DATA) public data: DialogData
	) {}

	onNoClick(): void {
		this.dialogRef.close();
	}

}