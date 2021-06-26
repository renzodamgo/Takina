
export interface PlaylistResponse {
	code: string;
    data: Playlist;
    message: string;
    status: string;
}

export interface PlaylistsResponse {
	code: string;
    data: Playlist[];
    message: string;
    status: string;
}

export interface PlaylistJson {
	creacion: string,
	descripcion: string,
	duracion: number,
	id: number,
	nombre: string,
	numCanciones: number,
	usuarioId: number,
}

export class Playlist {
	static playlistFromJson(obj: PlaylistJson){
		return new Playlist(
			obj['creacion'],
			obj['descripcion'],
			obj['duracion'],
			obj['id'],
			obj['nombre'],
			obj['numCanciones'],
			obj['usuarioId'],
		);
	}

	constructor(
		public creacion: string,
		public descripcion: string,
		public duracion: number,
		public id: number,
		public nombre: string,
		public numCanciones: number,
		public usuarioId: number,
	) {}
}

export class CreatePlaylist {
	static createPlaylistFromPlaylist(obj: Playlist){
		return new CreatePlaylist(
			obj.descripcion,
			obj.nombre,
			obj.usuarioId
		);
	}

	constructor(
		public descripcion: string,
		public nombre: string,
		public usuarioId: number,
	) {}
}

export class EditPlaylist {
	static editPlaylistFromPlaylist(obj: Playlist){
		return new EditPlaylist(
			obj.descripcion,
			obj.id,
			obj.nombre
		);
	}

	constructor(
		public descripcion: string,
		public id: number,
		public nombre: string,
	) {}
}